import java.util.HashMap;
import java.util.Map;


class RoomInventory {

    private HashMap<String, Integer> inventory;

    
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Standard", 10);
        inventory.put("Deluxe", 5);
        inventory.put("Suite", 2);
    }

    
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

   
    public void updateAvailability(String roomType, int change) {

        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Not enough rooms available for " + roomType);
            return;
        }

        inventory.put(roomType, updated);
    }

   
    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}



public class Exp3_BookMyStay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();


        System.out.println("\nBooking 2 Deluxe rooms...");
        inventory.updateAvailability("Deluxe", -2);

        inventory.displayInventory();


        System.out.println("\nCancelling 1 Deluxe room...");
        inventory.updateAvailability("Deluxe", 1);

        inventory.displayInventory();
    }
}
