package movie_booking_system;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat{
    private Integer seatNo;
    private SeatType seatType;
}
