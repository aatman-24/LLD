package movie_booking_system.payment;

import movie_booking_system.Booking;

public class DefaultPayment implements Payment {
    @Override
    public Boolean pay(double amount) {
        return true;
    }
}
