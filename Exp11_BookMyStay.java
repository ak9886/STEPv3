import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class HotelInventory {
    private HashMap<String, Integer> rooms = new HashMap<>();

    public HotelInventory() {
        rooms.put("Single", 10);
        rooms.put("Double", 5);
        rooms.put("Suite", 2);
    }

    public synchronized void bookRoom(String type, int quantity) throws InvalidBookingException {
        if (!rooms.containsKey(type)) throw new InvalidBookingException("Invalid room type: " + type);
        int available = rooms.get(type);
        if (quantity <= 0) throw new InvalidBookingException("Booking quantity must be greater than 0");
        if (quantity > available) throw new InvalidBookingException("Not enough rooms available. Requested: " + quantity + ", Available: " + available);
        rooms.put(type, available - quantity);
        System.out.println(Thread.currentThread().getName() + " booked " + quantity + " " + type + " room(s).");
    }

    public synchronized void displayAvailability() {
        System.out.println("Current Room Availability:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
        System.out.println();
    }
}

class BookingTask implements Runnable {
    private HotelInventory inventory;
    private String roomType;
    private int quantity;

    public BookingTask(HotelInventory inventory, String roomType, int quantity) {
        this.inventory = inventory;
        this.roomType = roomType;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        try {
            inventory.bookRoom(roomType, quantity);
        } catch (InvalidBookingException e) {
            System.out.println(Thread.currentThread().getName() + " failed: " + e.getMessage());
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        HotelInventory inventory = new HotelInventory();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable[] tasks = new Runnable[] {
            new BookingTask(inventory, "Single", 3),
            new BookingTask(inventory, "Double", 2),
            new BookingTask(inventory, "Single", 4),
            new BookingTask(inventory, "Suite", 1),
            new BookingTask(inventory, "Double", 4),
            new BookingTask(inventory, "Single", 5)
        };

        for (Runnable task : tasks) {
            executor.submit(task);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        inventory.displayAvailability();
        System.out.println("Concurrent booking simulation complete.");
    }
}
