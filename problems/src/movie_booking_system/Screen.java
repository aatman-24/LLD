package movie_booking_system;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    private Integer screenNo;
    private Theater theater;
    private List<Seat> seats;
}
