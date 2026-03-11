import java.util.HashMap;
import java.util.Map;

class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Standard", 10);
        inventory.put("Deluxe", 5);
        inventory.put("Suite", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return inventory;
    }
}

class SearchService {
    private RoomInventory inventory;
    private HashMap<String, Room> rooms;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
        rooms = new HashMap<>();
        rooms.put("Standard", new Room("Standard", 100.0, "WiFi, TV"));
        rooms.put("Deluxe", new Room("Deluxe", 180.0, "WiFi, TV, Mini Bar"));
        rooms.put("Suite", new Room("Suite", 300.0, "WiFi, TV, Mini Bar, Living Area"));
    }

    public void searchRooms() {
        Map<String, Integer> availability = inventory.getAllAvailability();
        for (String type : availability.keySet()) {
            int count = availability.get(type);
            if (count > 0) {
                Room room = rooms.get(type);
                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: $" + room.getPrice());
                System.out.println("Amenities: " + room.getAmenities());
                System.out.println("Available: " + count);
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        SearchService searchService = new SearchService(inventory);
        searchService.searchRooms();
    }
}
