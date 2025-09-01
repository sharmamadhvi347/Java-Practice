import java.util.*;

class Room {
    String roomNumber;
    String roomType;
    double pricePerNight;
    boolean isAvailable;
    int maxOccupancy;

    Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.maxOccupancy = maxOccupancy;
    }
}

class Guest {
    String guestId;
    String guestName;
    String phoneNumber;
    String email;
    String[] bookingHistory;
    int historyCount;

    Guest(String guestId, String guestName, String phoneNumber, String email, int maxBookings) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[maxBookings];
        this.historyCount = 0;
    }

    void addBooking(String bookingId) {
        if (historyCount < bookingHistory.length) {
            bookingHistory[historyCount++] = bookingId;
        }
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    String checkInDate;
    String checkOutDate;
    double totalAmount;

    static int totalBookings = 0;
    static double hotelRevenue = 0;
    static String hotelName = "Grand Palace";

    Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, double totalAmount) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        totalBookings++;
        hotelRevenue += totalAmount;
    }

    static double getTotalRevenue() {
        return hotelRevenue;
    }

    static double getOccupancyRate(Room[] rooms) {
        int occupied = 0;
        for (Room r : rooms) {
            if (!r.isAvailable) occupied++;
        }
        return (double) occupied / rooms.length * 100;
    }

    static String getMostPopularRoomType(List<Booking> bookings) {
        Map<String, Integer> freq = new HashMap<>();
        for (Booking b : bookings) {
            freq.put(b.room.roomType, freq.getOrDefault(b.room.roomType, 0) + 1);
        }
        String popular = "";
        int max = 0;
        for (String type : freq.keySet()) {
            if (freq.get(type) > max) {
                max = freq.get(type);
                popular = type;
            }
        }
        return popular;
    }
}

class HotelSystem {
    List<Room> rooms = new ArrayList<>();
    List<Guest> guests = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    Room checkAvailability(String roomType, int guests) {
        for (Room r : rooms) {
            if (r.isAvailable && r.roomType.equalsIgnoreCase(roomType) && r.maxOccupancy >= guests) {
                return r;
            }
        }
        return null;
    }

    Booking makeReservation(String bookingId, Guest guest, String roomType, int guests, String checkIn, String checkOut, int nights) {
        Room r = checkAvailability(roomType, guests);
        if (r == null) return null;
        r.isAvailable = false;
        double amount = r.pricePerNight * nights;
        Booking b = new Booking(bookingId, guest, r, checkIn, checkOut, amount);
        bookings.add(b);
        guest.addBooking(bookingId);
        return b;
    }

    void cancelReservation(String bookingId) {
        for (Booking b : bookings) {
            if (b.bookingId.equals(bookingId)) {
                b.room.isAvailable = true;
                Booking.hotelRevenue -= b.totalAmount; // Correctly decrease hotel revenue
                bookings.remove(b);
                return;
            }
        }
    }

    double calculateBill(String bookingId) {
        for (Booking b : bookings) {
            if (b.bookingId.equals(bookingId)) return b.totalAmount;
        }
        return 0;
    }

    void displayBookings() {
        for (Booking b : bookings) {
            System.out.println("Booking " + b.bookingId + " Guest: " + b.guest.guestName + " Room: " + b.room.roomNumber + " Amount: " + b.totalAmount);
        }
    }
}

public class Main_Q3 {
    public static void main(String[] args) {
        HotelSystem system = new HotelSystem();

        system.rooms.add(new Room("101", "Single", 2000, 1));
        system.rooms.add(new Room("102", "Double", 3500, 2));
        system.rooms.add(new Room("201", "Suite", 8000, 4));
        system.rooms.add(new Room("202", "Double", 3500, 2));
        system.rooms.add(new Room("301", "Suite", 8000, 4));

        Guest g1 = new Guest("G1", "Alice", "1234567890", "alice@mail.com", 5);
        Guest g2 = new Guest("G2", "Bob", "9876543210", "bob@mail.com", 5);
        system.guests.add(g1);
        system.guests.add(g2);

        Booking b1 = system.makeReservation("B1", g1, "Double", 2, "2025-09-05", "2025-09-07", 2);
        Booking b2 = system.makeReservation("B2", g2, "Suite", 3, "2025-09-10", "2025-09-12", 2);

        system.displayBookings();
        System.out.println("Bill for B1: " + system.calculateBill("B1"));

        system.cancelReservation("B1");
        system.displayBookings();

        System.out.println("Total Revenue: " + Booking.getTotalRevenue());
        System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(system.rooms.toArray(new Room[0]))); // Pass rooms as an array
        System.out.println("Most Popular Room Type: " + Booking.getMostPopularRoomType(system.bookings)); // Pass bookings list
    }
}
