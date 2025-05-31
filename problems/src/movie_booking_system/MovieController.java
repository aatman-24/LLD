package movie_booking_system;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Data
@Getter
@Setter
public class MovieController {

    private Map<String, List<Movie>> cityBasedMovies;
    private List<Movie> allMovies;

    public MovieController() {
        cityBasedMovies = new HashMap<>(10);
        allMovies = new ArrayList<>(10);
    }

    public void addMovie(String city, Movie movie) {
        allMovies.add(movie);
        cityBasedMovies.putIfAbsent(city, List.of(movie));
    }

    public List<Movie> getMoviesBasedOnCity(String city) {
        return cityBasedMovies.get(city);
    }

    public Optional<Movie> getMovie(String movieName) {
        return allMovies.stream().filter((Movie movie) -> movie.getMovieName().equals(movieName)).findFirst();
    }


    // TODO:
    //+ removeMovie(Movie mv): boolean
}
