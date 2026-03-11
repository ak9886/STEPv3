import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class HotelInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Integer> rooms = new HashMap<>();
    private HashMap<String, Integer> bookingHistory = new HashMap<>();

    public HotelInventory() {
        rooms.put("Single", 10);
        rooms.put("Double", 5);
        rooms.put("Suite", 2);
    }

    public void bookRoom(String type, int quantity) throws InvalidBookingException {
        if (!rooms.containsKey(type)) throw new InvalidBookingException("Invalid room type: " + type);
        int available = rooms.get(type);
        if (quantity <= 0) throw new InvalidBookingException("Booking quantity must be greater than 0");
        if (quantity > available) throw new InvalidBookingException("Not enough rooms available. Requested: " + quantity + ", Available: " + available);
        rooms.put(type, available - quantity);
        bookingHistory.put(type, bookingHistory.getOrDefault(type, 0) + quantity);
        System.out.println(quantity + " " + type + " room(s) booked successfully.");
    }

    public void displayAvailability() {
        System.out.println("Current Room Availability:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
        System.out.println("Booking History:");
        for (String type : bookingHistory.keySet()) {
            System.out.println(type + ": " + bookingHistory.get(type));
        }
        System.out.println();
    }

    public void saveState(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save system state: " + e.getMessage());
        }
    }

    public static HotelInventory loadState(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            HotelInventory inventory = (HotelInventory) ois.readObject();
            System.out.println("System state restored successfully.");
            return inventory;
        } catch (FileNotFoundException e) {
            System.out.println("No saved state found. Starting fresh.");
            return new HotelInventory();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to restore system state: " + e.getMessage());
            return new HotelInventory();
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    private static final String STATE_FILE = "hotel_inventory.dat";

    public static void main(String[] args) {
        HotelInventory inventory = HotelInventory.loadState(STATE_FILE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            inventory.displayAvailability();
            System.out.print("Enter operation (book/exit): ");
            String operation = scanner.nextLine();
            if (operation.equalsIgnoreCase("exit")) break;
            if (operation.equalsIgnoreCase("book")) {
                System.out.print("Enter room type (Single/Double/Suite): ");
                String type = scanner.nextLine();
                System.out.print("Enter quantity: ");
                String qtyInput = scanner.nextLine();
                try {
                    int quantity = Integer.parseInt(qtyInput);
                    inventory.bookRoom(type, quantity);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Please enter a numeric value.");
                } catch (InvalidBookingException e) {
                    System.out.println("Booking failed: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid operation.");
            }
        }
        scanner.close();
        inventory.saveState(STATE_FILE);
        System.out.println("Thank you for using Book My Stay App!");
    }
}
