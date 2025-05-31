package movie_booking_system;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Show {

    private static Long counter = 1L;

    private Long showId;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Seat> bookedSeat;

    public Show(Movie movie, Screen screen, LocalDateTime startTime, LocalDateTime endTime) {
        this.showId = counter++;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeat = new ArrayList<>(10);
    }

    public List<Seat> getAvailableSeats() {
        return screen.getSeats().stream().filter((Seat seat) -> !bookedSeat.contains(seat)).toList();
    }

    public Boolean bookSeats(List<Seat> seats) {

        // validate all seats are not booked
        boolean booked = bookedSeat.stream().anyMatch(seats::contains);

        if(booked) {
            System.out.println("One of seat is already booked!!");
            return false;
        }

        // otherwise add all given seats to bookedSeat array
        bookedSeat.addAll(seats);

        return true;
    }

}
