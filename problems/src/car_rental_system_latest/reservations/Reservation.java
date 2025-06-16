package car_rental_system_latest.reservations;

import car_rental_system_latest.Transaction;
import car_rental_system_latest.User;
import car_rental_system_latest.Vehicle;

import java.time.LocalDateTime;

public abstract class Reservation {

    private Vehicle vehicle;
    private User user;
    private ReservationStatus reservationStatus;
    private ReservationType reservationType;
    private Transaction transaction;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    public Reservation(Vehicle vehicle, User user, LocalDateTime fromTime, LocalDateTime toTime, ReservationType reservationType) {
        this.vehicle = vehicle;
        this.user = user;
        this.reservationType = reservationType;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public abstract Double getTotalFare();
}
