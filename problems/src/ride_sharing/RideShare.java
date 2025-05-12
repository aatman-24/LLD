package ride_sharing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RideShare {

    private static RideShare rideShare = null;
    private ConcurrentHashMap<Long, User> users;
    private ConcurrentHashMap<Long, Ride> rides;

    private RideShare() {
        users = new ConcurrentHashMap<Long, User>(20);
        rides = new ConcurrentHashMap<Long, Ride>(20);
    }

    public User registerUser(String name, GenderEnum gender, Integer age) {
        User user = new User(name, gender, age);
        users.put(user.getUserId(), user);
        return user;
    }

    public Vehicle registerVehicle(User ownedBy, String vehicleNum, VehicleType vehicleType, String nameOfCar) {
        Vehicle vehicle = new Vehicle(vehicleNum, vehicleType, ownedBy, nameOfCar);
        ownedBy.addVehicle(vehicle);
        return vehicle;
    }

    public Ride offerRide(User offeredBy, String origin, String destination, Vehicle vehicle, LocalDateTime startTime, Duration rideTime, Integer availableSeat) {
        if(!users.containsKey(offeredBy.getUserId())) {
            throw new UserNotFoundException("User not found with userId: " + offeredBy.getUserId());
        }
        Ride ride = new Ride(origin, destination, vehicle, startTime, rideTime, availableSeat);
        rides.put(ride.getRideNo(), ride);
        return ride;
    }

    public Ride getRide(Long rideNo) {
        return rides.get(rideNo);
    }

    public Ride selectRide(String origin, String destination, SelectionStrategyType selectionStrategyType) {
        List<Ride> rides = getRides(origin, destination);
        if(rides.isEmpty()) {
            System.out.println("Ride not found");
            return null;
        }
        if(selectionStrategyType == SelectionStrategyType.EARLIEST) {
            return RideSelection.getEndingFirst(rides);
        } else {
            return RideSelection.getFastestRide(rides);
        }
    }

    private List<Ride> getRides(String origin, String destination) {
       return rides.values().stream().filter(ride -> ride.getOrigin().equals(origin) && ride.getDestination().equals(destination) && ride.getAvailableSeat() > 0).toList();
    }

    public static RideShare getInstance() {
        if (rideShare == null) {
            rideShare = new RideShare();
        }
        return rideShare;
    }
}
