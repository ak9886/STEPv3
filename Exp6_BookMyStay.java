import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class BookingService {
    private RoomInventory inventory;
    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomAllocations = new HashMap<>();
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processReservation(Reservation reservation) {
        String type = reservation.getRoomType();

        if (inventory.getAvailability(type) <= 0) {
            System.out.println("No rooms available for " + type);
            return;
        }

        String roomId;
        do {
            roomId = type.substring(0, 1).toUpperCase() + idCounter++;
        } while (allocatedRoomIds.contains(roomId));

        allocatedRoomIds.add(roomId);

        roomAllocations.putIfAbsent(type, new HashSet<>());
        roomAllocations.get(type).add(roomId);

        inventory.decrement(type);

        System.out.println("Reservation confirmed for " + reservation.getGuestName() + " Room ID: " + roomId);
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        BookingService bookingService = new BookingService(inventory);

        queue.addRequest(new Reservation("Alice", "Standard"));
        queue.addRequest(new Reservation("Bob", "Deluxe"));
        queue.addRequest(new Reservation("Charlie", "Suite"));

        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            bookingService.processReservation(r);
        }
    }
}
