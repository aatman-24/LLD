package car_rental_system_latest.reservations;

import car_rental_system_latest.User;
import car_rental_system_latest.Vehicle;

import java.time.LocalDateTime;

public class ReservationHourlyBasis extends Reservation{

    public ReservationHourlyBasis(Vehicle vehicle, User user, LocalDateTime fromTime, LocalDateTime toTime) {
        super(vehicle, user, fromTime, toTime, ReservationType.HOURLY);
    }

    @Override
    public Double getTotalFare() {
        return 0.0;
    }
}
