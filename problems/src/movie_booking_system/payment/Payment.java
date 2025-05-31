package movie_booking_system.payment;

import movie_booking_system.Booking;

public interface Payment {
    Boolean pay(double amount);
}
