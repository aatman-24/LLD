package car_rental_system_latest.reservations;

import car_rental_system_latest.User;
import car_rental_system_latest.Vehicle;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

public class ReservationDailyBasis extends  Reservation {

    public ReservationDailyBasis(Vehicle vehicle, User user, LocalDateTime fromTime, LocalDateTime toTime) {
        super(vehicle, user, fromTime, toTime, ReservationType.DAILY);
    }

    @Override
    public Double getTotalFare() {
        return 0.0;
    }
}
