package movie_booking_system;

import lombok.*;

import java.time.Duration;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
    private static Long count = 1L;

    private Long movieId;
    private String movieName;
    private Duration duration;

    public Movie(String movieName, Duration duration) {
        movieId = count++;
        this.movieName = movieName;
        this.duration = duration;
    }
}
