package movie_booking_system;

import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) {

        BookMyShow bookMyShow = BookMyShow.getInstance();

        List<Movie> movieList = bookMyShow.getMoviesBasedOnCity("Bangalore");
        System.out.println(movieList);

        List<Show> shows = bookMyShow.getAllShow(movieList.get(0), "Bangalore");
        System.out.println("All shows name: " + shows.size());

        Show show = shows.get(0);
        List<Seat> availableSeats = bookMyShow.getAvailableSeats(show);
        System.out.println("Available Seats: " + availableSeats);

        List<Seat> bookSeats =  List.of(availableSeats.get(2), availableSeats.get(3));

        Booking booking = bookMyShow.bookTicket(show, bookSeats, "John");
        System.out.println(booking);


    }
}
