## Design Problems: Moving Booking System (BookMyShow)

---

### Requirement

```textile
The system should allow users to view the list of movies playing in different theaters.
Users should be able to select a movie, theater, and show timing to book tickets.
The system should display the seating arrangement of the selected show and allow users to choose seats.
Users should be able to make payments and confirm their booking.
The system should handle concurrent bookings and ensure seat availability is updated in real-time.
The system should support different types of seats (e.g., normal, premium) and pricing.
The system should allow theater administrators to add, update, and remove movies, shows, and seating arrangements.
The system should be scalable to handle a large number of concurrent users and bookings.
```

### Identify Flow

```textile
User enter a city name. 

We get all the movies which are playing currently in the city. 

User select one of the movie, based on that we show all available shows for that city in UI. 

User select one of the show, and select all the seats, and do the booking. 

Ticket needs to be generated and we send it to the users. 

NOTE: Such Movie Booking application basically centered around the city. 

```

### Entity LookUp

```textile
User
Movie
Theater
Screen
Show
Seat
Booking 
Payment
City (Basically, address/location but here we are using for mapping purpose)

```

### Relationship Lookup

```textile
Theater (1) - Shows (N) 		// Theater can have multiple shows
Theater (1) - Screen (N) 		// Theater can have multiple screens
Show (1) - Movie (1) 			// Each show is associated with one movie
Show (1) - Theater (1)			// Each show is associated with one Theater
Show (1) - Screen (1)			// Show run on particular screen
Show (1) - Seats (N) 			// Shows have all booked seats information
Screen(1) - Seats (N)			// Screen have all the seats information, but no of booked seats info we track show wise
Booking(1) - User (1)
Booking(1) - Movie (1)
Booking(1) - Screen (1)
Booking(1) - Payment(1)
```

### Applying SOLID Principle | Design Patterns

```textile
1. BookMyShow (Singleton)

2. Based on design, we understand that paricular location/city can have mulitple movies playing at a time, but where we are storing this ? A) We can directly store inside the BookMyShow, but B) It is better if we store all Movie related stuff in other class => MovieController: Controls the movie related stuff, store all the movies and its relevant city

3. Same for the Theater, we are not going to store direclty inside the BookMyShow, better we have seperate class for that. 
TheaterController: Controls the theater related stuff

4. Payment -> Strategy Design Pattern

5. Basically, Theater have multiple screens, and each screen have seats, so when customer book the seat we are not going to book 
from the screen class, because it is possible that multiple shows are going in parallel, so we need to keep track of booked seats
info inside the Show class itself.
```

### Class Diagram

```textile
Movie
- id: Long
- movieName: String
- duration: Duration

MovieController
- locationBasedMovies: <String, List<Movie>> 	// <city_name, List<Movie>>
- movies: List<Movie>
--
+ addMovie(String city, Movie mv): void
+ getMoviesBasedOnCity(String city): List<Movie>
+ getMovie(String movieName): Movie
+ removeMovie(Movie mv): boolean

Theater
- Id: Long
- name: String
- city: String
- shows: List<Show>
- screens: List<Screen>
--
+ addShow(Show show): void
+ addScreen(Screen screen): void


TheaterController
- theters: <String, List<Theater>>			// <city_name, List<Theater>>
- allTheaters: List<Theater>
--
+ addTheater(String city, Theater th): void
+ getTheatersOfCity(String City): List<Theater>
+ getAllShows(Movie movie, String city): List<Show>
+ removeTheater(String city, Theater th): Boolean

Show
- movie: Movie
- screen: Screen
- startTime: DateTime
- endTime: DateTime
- bookedSeats: List<Seat>
--
+ getAvailableSeats(): List<Seat>
+ bookSeats(List<Seat> seats): Boolean

Screen
- theater: Theater
- seats: List<Seat>

Seat
- seatType: SeatType
- seatNo: String

Booking
- movie: Movie
- show: Show
- bookedSeats: List<Seat>
- payment: Payment

Payment
- paymentNo: Long
- status: PaymentStatus

BookMyShow
- bookMyShow: BookMyShow
- movieController: MovieController
- theaterController: TheaterController
--
+ getMoviesByCity(String cityName): List<Movies>
+ getShows(String cityName, Movie movie): List<Show> 
+ getAvailableSeats(Show show): List<Seats>
+ bookSeat(Show show, List<Seat> seats): Booking

```

### Source Code

```java
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
```

```java

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

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat{
    private Integer seatNo;
    private SeatType seatType;
}

public enum SeatType {
    SILVER,
    GOLD,
    PREMIUM;
}


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
```

```java
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


public interface Payment {
    Boolean pay(double amount);
}

public class DefaultPayment implements Payment {
    @Override
    public Boolean pay(double amount) {
        return true;
    }
}
```

```java
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
```



### ✅ Follow UP Question(Concurrency):

Multiple users can select the same movie seats simultaneously. However:

* Only **one user** should be allowed to **book** the seat.
* Others should be redirected to the selection page if the seat is already booked.
* The user has **5 minutes** to complete the booking (e.g., reach the payment page).
* After 5 minutes, the seat should be released if not booked.

---

### 🔒 Locking Mechanisms:

#### 1. **Pessimistic Locking**:

* A **lock is acquired immediately** when a resource (seat) is read.

* Other transactions are **blocked** until the current transaction completes (either booking or timeout).

* **Not ideal** here because:
  
  * If a user selects multiple seats and doesn’t proceed, those seats are **unnecessarily locked**, blocking others from even trying.

#### 2. **Optimistic Locking**:

* Allows **concurrent reads** without locking.
* On update, the system **checks a version number** to ensure the seat hasn't changed.
* If version mismatch is found → **update fails** → user is redirected to seat selection again.

---

### ✅ Flow using Optimistic Lock:

#### Step-by-step:

1. Both User1 and User2 read **Seat1 with version X1**.

2. Both try to book.

3. User2 gets DB access first:
   
   * Compares version: **DB version X1 == Read version X1** ✅
   * Booking proceeds: **seat booked**, version updated to **X2**

4. User1 tries to book:
   
   * Compares version: **DB version X2 != Read version X1** ❌
   * Booking fails → redirect to seat selection page

#### 💡 Tech:

* Use **`@Version` annotation** in JPA (Hibernate) or implement a **manual version check** in SQL (`WHERE version = ?`).
* Booking logic should be **synchronized** or **atomic** using transactions.

```java
@Transactional
public void bookSeat(Seat seat) {
   // Compare version
   // If match, update status and increment version
   // Else, throw error / redirect
}
```

And yes..... To handle this situation, we need to use synchornized block on method bookSeat().

---

### ⏳ Timer using Redis:

#### Why Redis?

* Redis supports **key expiry** and is **fast**, making it perfect for handling time-based locks or reservations.

#### How to use:

1. When User selects a seat, store in Redis:
   
   ```redis
   SETEX seat1:user1 300 user1_id
   ```
   
   This key auto-expires in **300 seconds (5 minutes)**.

2. When booking is confirmed:
   
   * **Delete the Redis key**
   * Update DB with booked status and new version.

3. If user doesn’t complete booking:
   
   * Redis key expires → seat is marked **available** again.

#### Optional Enhancement:

* Use a background job or event listener to **clean up expired reservations** (if Redis is decoupled).

---

### 🛠 Summary of Tools & Concepts:

| Concern                     | Solution                                 |
| --------------------------- | ---------------------------------------- |
| Concurrency in seat booking | **Optimistic Locking**                   |
| Timer for booking window    | **Redis `SETEX` key expiry**             |
| Method-level thread safety  | `synchronized` block or `@Transactional` |
| Prevent stale updates       | Compare seat **version**                 |
