package movie_booking_system;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class TheaterController {

    private Map<String, List<Theater>> cityBasedTheaters;
    private List<Theater> theaterList;

    public TheaterController() {
        cityBasedTheaters = new HashMap<>();
        theaterList = new ArrayList<>(10);
    }

    public void addTheater(String city, Theater theater) {
            theaterList.add(theater);
            cityBasedTheaters.putIfAbsent(city, List.of(theater));
//            cityBasedTheaters.get(city).add(theater);
    }

    public List<Theater> getTheatersOfCity(String city){
        return cityBasedTheaters.get(city);
    }

    List<Show> getAllShow(Movie movie, String city) {

        //get all the theater of this city
        List<Show> allMatchedShows = new ArrayList<>();

        List<Theater> Theaters = cityBasedTheaters.get(city);

        //filter the Theaters which run this movie

        for(Theater Theater : Theaters) {

            List<Show> givenMovieShows = new ArrayList<>();
            List<Show> shows = Theater.getShows();

            for(Show show : shows) {
                if(show.getMovie().getMovieId().equals(movie.getMovieId())) {
                    givenMovieShows.add(show);
                }
            }

            allMatchedShows.addAll(givenMovieShows);

        }

        return allMatchedShows;
    }

//    Using Stream API
//    Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
//        return cityVsTheatre.get(city).stream()
//                .map(theatre -> Map.entry(
//                        theatre,
//                        theatre.getShows().stream()
//                                .filter(show -> show.getMovie().getMovieId() == movie.getMovieId())
//                                .collect(Collectors.toList())
//                ))
//                .filter(entry -> !entry.getValue().isEmpty())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

//+ removeTheater(String city, Theater th): Boolean

}
