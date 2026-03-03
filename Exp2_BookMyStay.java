abstract class Room {
private String type;
private int beds;
private double size;
private double price;

public Room(String type, int beds, double size, double price) {
    this.type = type;
    this.beds = beds;
    this.size = size;
    this.price = price;
}

public String getType() {
    return type;
}

public int getBeds() {
    return beds;
}

public double getSize() {
    return size;
}

public double getPrice() {
    return price;
}

public abstract String getDescription();


}

class SingleRoom extends Room {
public SingleRoom() {
super("Single Room", 1, 20.0, 3000.0);
}

public String getDescription() {
    return "Ideal for one guest";
}


}

class DoubleRoom extends Room {
public DoubleRoom() {
super("Double Room", 2, 35.0, 5000.0);
}


public String getDescription() {
    return "Perfect for two guests";
}


}

class SuiteRoom extends Room {
public SuiteRoom() {
super("Suite Room", 3, 60.0, 9000.0);
}


public String getDescription() {
    return "Luxury accommodation with premium amenities";
}


}

public class UseCase2RoomInitialization {
public static void main(String[] args) {
System.out.println("=====================================");
System.out.println("Welcome to Book My Stay");
System.out.println("Hotel Booking Management System v2.1");
System.out.println("=====================================");

    Room single = new SingleRoom();
    Room doubleRoom = new DoubleRoom();
    Room suite = new SuiteRoom();

    int singleAvailability = 5;
    int doubleAvailability = 3;
    int suiteAvailability = 2;

    System.out.println("\nRoom Details and Availability:\n");

    System.out.println(single.getType());
    System.out.println("Beds: " + single.getBeds());
    System.out.println("Size: " + single.getSize() + " sqm");
    System.out.println("Price: ₹" + single.getPrice());
    System.out.println("Description: " + single.getDescription());
    System.out.println("Available Rooms: " + singleAvailability);
    System.out.println();

    System.out.println(doubleRoom.getType());
    System.out.println("Beds: " + doubleRoom.getBeds());
    System.out.println("Size: " + doubleRoom.getSize() + " sqm");
    System.out.println("Price: ₹" + doubleRoom.getPrice());
    System.out.println("Description: " + doubleRoom.getDescription());
    System.out.println("Available Rooms: " + doubleAvailability);
    System.out.println();

    System.out.println(suite.getType());
    System.out.println("Beds: " + suite.getBeds());
    System.out.println("Size: " + suite.getSize() + " sqm");
    System.out.println("Price: ₹" + suite.getPrice());
    System.out.println("Description: " + suite.getDescription());
    System.out.println("Available Rooms: " + suiteAvailability);
}
}

