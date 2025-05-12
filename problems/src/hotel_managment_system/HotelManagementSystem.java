package hotel_managment_system;

import hotel_managment_system.payment.Payment;
import hotel_managment_system.payment.PaymentFactory;
import hotel_managment_system.payment.PaymentType;
import hotel_managment_system.rooms.Room;
import hotel_managment_system.rooms.RoomFactory;
import hotel_managment_system.rooms.RoomType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class HotelManagementSystem {

    private static HotelManagementSystem instance;
    private List<Room> roomList;
    private List<Reservation> reservationList;

    public HotelManagementSystem() {
        this.roomList = new CopyOnWriteArrayList<>();
        this.reservationList = new CopyOnWriteArrayList<>();
    }

    public static HotelManagementSystem getInstance() {
        if (instance == null) {
            instance = new HotelManagementSystem();
        }
        return instance;
    }

    // Managment methods
    public boolean addRoom(Integer roomNum, RoomType roomType) {
        Room room = RoomFactory.createRoom(roomNum, roomType);
        return this.roomList.add(room);
    }

    public void removeRoom(Room room) {
        this.roomList.remove(room);
    }

    public List<Room> getAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        Set<Room> collect = reservationList.stream().filter(reservation -> reservation.getCheckInDate().isBefore(checkInDate) && reservation.getCheckOutDate().isAfter(checkOutDate)).map(Reservation::getRoom).collect(Collectors.toSet());
        return roomList.stream().filter(room -> !collect.contains(room)).collect(Collectors.toList());
    }

    // Reservation methods
    public synchronized Reservation makeReservation(Guest guest, Room room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        Reservation reservation = new Reservation(guest, room, checkInDate, checkOutDate);
        this.reservationList.add(reservation);
        return reservation;
    }

    // checkin-checkout methods
    public void checkIn(Reservation reservation) {
        reservation.getRoom().setOccupied(true);
    }

    public void checkOut(Reservation reservation, PaymentType paymentType) {
        Payment payment = PaymentFactory.getPaymentMethod(paymentType);
        payment.pay((reservation.getTotalFare()).intValue());
        this.reservationList.remove(reservation);
    }
}
