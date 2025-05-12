package hotel_managment_system;

import hotel_managment_system.rooms.Room;

import java.time.LocalDateTime;

public class Util {
    public static Double calculateFare(Room room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        int totalDays = checkInDate.getDayOfMonth() - checkOutDate.getDayOfMonth();
        return (double) (room.getRoomPrice() * totalDays);
    }
}
