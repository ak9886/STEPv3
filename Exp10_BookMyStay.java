import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String message) {
        super(message);
    }
}

class HotelInventory {
    private HashMap<String, Integer> rooms = new HashMap<>();
    private Stack<String> releasedRooms = new Stack<>();
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
        for (int i = 0; i < quantity; i++) releasedRooms.push(type + "-" + (available - i));
        System.out.println(quantity + " " + type + " room(s) booked successfully.");
    }

    public void cancelBooking(String type, int quantity) throws BookingNotFoundException {
        if (!bookingHistory.containsKey(type) || bookingHistory.get(type) < quantity) {
            throw new BookingNotFoundException("Cannot cancel. Not enough bookings for room type: " + type);
        }
        int available = rooms.get(type);
        rooms.put(type, available + quantity);
        bookingHistory.put(type, bookingHistory.get(type) - quantity);
        for (int i = 0; i < quantity; i++) releasedRooms.pop();
        System.out.println(quantity + " " + type + " room(s) cancelled successfully.");
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
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        HotelInventory inventory = new HotelInventory();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            inventory.displayAvailability();
            System.out.print("Enter operation (book/cancel/exit): ");
            String operation = scanner.nextLine();
            if (operation.equalsIgnoreCase("exit")) break;
            System.out.print("Enter room type (Single/Double/Suite): ");
            String type = scanner.nextLine();
            System.out.print("Enter quantity: ");
            String qtyInput = scanner.nextLine();
            try {
                int quantity = Integer.parseInt(qtyInput);
                if (operation.equalsIgnoreCase("book")) {
                    inventory.bookRoom(type, quantity);
                } else if (operation.equalsIgnoreCase("cancel")) {
                    inventory.cancelBooking(type, quantity);
                } else {
                    System.out.println("Invalid operation.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a numeric value.");
            } catch (InvalidBookingException | BookingNotFoundException e) {
                System.out.println("Operation failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("Thank you for using Book My Stay App!");
    }
}
