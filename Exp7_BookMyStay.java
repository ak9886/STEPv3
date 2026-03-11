import java.util.*;

class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        for (AddOnService s : services) {
            total += s.getCost();
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        System.out.println("Services for Reservation " + reservationId + ":");
        for (AddOnService s : services) {
            System.out.println(s.getName() + " - $" + s.getCost());
        }
        System.out.println("Total Add-On Cost: $" + calculateTotalCost(reservationId));
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId1 = "R101";
        String reservationId2 = "R102";

        manager.addService(reservationId1, new AddOnService("Breakfast", 20));
        manager.addService(reservationId1, new AddOnService("Airport Pickup", 40));

        manager.addService(reservationId2, new AddOnService("Spa Access", 60));
        manager.addService(reservationId2, new AddOnService("Extra Bed", 30));

        manager.displayServices(reservationId1);
        manager.displayServices(reservationId2);
    }
}
