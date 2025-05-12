package hotel_managment_system;

import hotel_managment_system.rooms.Room;

import java.time.LocalDateTime;

public class Reservation {
    private Guest guest;
    private Room room;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Double totalFare;

    public Reservation(Guest guest, Room room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalFare = calculateFare();
    }

    private double calculateFare() {
        int totalDays = checkInDate.getDayOfMonth() - checkOutDate.getDayOfMonth();
        return room.getRoomPrice() * totalDays;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Double getTotalFare() {
        return totalFare;
    }
}
