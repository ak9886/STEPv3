import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room Type: " + roomType;
    }
}

class BookingHistory {
    private List<Reservation> confirmedReservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(confirmedReservations);
    }
}

class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void generateReport() {
        System.out.println("Booking History Report:");
        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }
    }

    public void generateSummaryByRoomType() {
        Map<String, Integer> summary = new HashMap<>();
        for (Reservation r : history.getAllReservations()) {
            summary.put(r.getRoomType(), summary.getOrDefault(r.getRoomType(), 0) + 1);
        }
        System.out.println("\nSummary by Room Type:");
        for (String type : summary.keySet()) {
            System.out.println(type + ": " + summary.get(type));
        }
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService(history);

        history.addReservation(new Reservation("R101", "Alice", "Standard"));
        history.addReservation(new Reservation("R102", "Bob", "Deluxe"));
        history.addReservation(new Reservation("R103", "Charlie", "Suite"));
        history.addReservation(new Reservation("R104", "Diana", "Standard"));

        reportService.generateReport();
        reportService.generateSummaryByRoomType();
    }
}
