package movie_booking_system;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Show show;
    private String customerName;
    private List<Seat> bookedSeats;
}
