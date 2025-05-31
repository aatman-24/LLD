package movie_booking_system;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Theater {

    private static Long counter = 1L;

    private Long theaterId;
    private String theaterName;
    private String city;
    private List<Show> shows;
    private List<Screen> screens;

    public Theater(String theaterName, String city) {
        this.theaterId = counter++;
        this.theaterName = theaterName;
        this.city = city;
        this.shows = new ArrayList<>(10);
        this.screens = new ArrayList<>(10);
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
