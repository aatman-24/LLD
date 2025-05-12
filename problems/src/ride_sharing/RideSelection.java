package ride_sharing;

import java.util.List;

public class RideSelection {

    public static Ride getFastestRide(List<Ride> rides) {
        return rides.stream().sorted((Ride rideA, Ride rideB) -> rideA.getRideTime().compareTo(rideB.getRideTime())).findFirst().orElse(null);
    }

    public static Ride getEndingFirst(List<Ride> rides) {
        return rides.stream().sorted((Ride rideA, Ride rideB) -> rideA.getEndTime().compareTo(rideB.getEndTime())).findFirst().orElse(null);
    }
}
