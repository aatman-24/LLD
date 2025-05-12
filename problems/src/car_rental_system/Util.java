package car_rental_system;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;

public class Util {

    public static Integer calculateDays(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) DAYS.between(startDate, endDate);
    }
}
