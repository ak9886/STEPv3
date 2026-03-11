import java.util.HashMap;
import java.util.Scanner;

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

    public void bookRoom(String type, int quantity) throws InvalidBookingException {
        if (!rooms.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }
        int available = rooms.get(type);
        if (quantity <= 0) {
            throw new InvalidBookingException("Booking quantity must be greater than 0");
        }
        if (quantity > available) {
            throw new InvalidBookingException("Not enough rooms available. Requested: " + quantity + ", Available: " + available);
        }
        rooms.put(type, available - quantity);
        System.out.println(quantity + " " + type + " room(s) booked successfully.");
    }

    public void displayAvailability() {
        System.out.println("Current Room Availability:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        HotelInventory inventory = new HotelInventory();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            inventory.displayAvailability();
            System.out.print("Enter room type to book (Single/Double/Suite) or 'exit' to quit: ");
            String type = scanner.nextLine();
            if (type.equalsIgnoreCase("exit")) break;
            System.out.print("Enter quantity: ");
            String qtyInput = scanner.nextLine();
            try {
                int quantity = Integer.parseInt(qtyInput);
                inventory.bookRoom(type, quantity);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a numeric value.");
            } catch (InvalidBookingException e) {
                System.out.println("Booking failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("Thank you for using Book My Stay App!");
    }
}
