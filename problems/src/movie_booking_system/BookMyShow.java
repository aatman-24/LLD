package movie_booking_system;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

    private static BookMyShow bookMyShow;
    private TheaterController theaterController;
    private MovieController movieController;

    private BookMyShow() {
        theaterController = new TheaterController();
        movieController = new MovieController();
        intializeBookMyShow();
    }

    private void intializeBookMyShow() {

        // added movie
        Movie movie = new Movie("Avengers", Duration.of(2, ChronoUnit.HOURS));
        movieController.addMovie("Bangalore", movie);
        movieController.addMovie("Chennai", movie);

        // creating theater
        Theater theater = new Theater("PVR", "Bangalore");
        List<Seat> seats = new ArrayList<>(5);
        seats.add(new Seat(1, SeatType.SILVER));
        seats.add(new Seat(2, SeatType.SILVER));
        seats.add(new Seat(3, SeatType.GOLD));
        seats.add(new Seat(4, SeatType.GOLD));
        seats.add(new Seat(5, SeatType.PREMIUM));
        Screen screen = new Screen(1, theater, seats);
        theater.addScreen(screen);

        // adding startTime and endTime as random value.
        theater.addShow(new Show(movie, screen, LocalDateTime.now(), LocalDateTime.now()));
        theaterController.addTheater("Bangalore", theater);
    }

    public void addTheater(String city, Theater theater) {
        theaterController.addTheater(city, theater);
    }

    public void addMovie(String city, Movie movie) {
        movieController.addMovie(city, movie);
    }

    public List<Show> getAllShow(Movie movie, String city) {
        return theaterController.getAllShow(movie, city);
    }

    public List<Theater> getTheatersOfCity(String city) {
        return theaterController.getTheatersOfCity(city);
    }

    public List<Movie> getMoviesBasedOnCity(String city) {
        return movieController.getMoviesBasedOnCity(city);
    }

    public List<Seat> getAvailableSeats(Show show) {
        return show.getAvailableSeats();
    }

    public synchronized Booking bookTicket(Show show, List<Seat> seats, String customerName) {
        if(show.bookSeats(seats)) {
            return new Booking(show, customerName, seats);
        }
        return null;
    }

    public static BookMyShow getInstance() {
        if (bookMyShow == null) {
            bookMyShow = new BookMyShow();
        }
        return bookMyShow;
    }
}
