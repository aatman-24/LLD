package ride_sharing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Ride {
    private Long rideNo;
    private String origin;
    private String destination;
    private Vehicle vehicle;
    private Integer availableSeat;
    private LocalDateTime startTime;
    private Duration rideTime;
    private List<User> takenBy;

    public Ride(String origin, String destination, Vehicle vehicle, LocalDateTime startTime, Duration rideTime, Integer availableSeat) {
        this.rideNo = generateRideNo();
        this.origin = origin;
        this.destination = destination;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.rideTime = rideTime;
        this.availableSeat = availableSeat;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public void takenByUser(User user) {
        takenBy.add(user);
        this.availableSeat -= 1;
    }

    public Long getRideNo() {
        return rideNo;
    }

    public void setRideNo(Long rideNo) {
        this.rideNo = rideNo;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getRideTime() {
        return rideTime;
    }

    public void setRideTime(Duration rideTime) {
        this.rideTime = rideTime;
    }

    public List<User> getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(List<User> takenBy) {
        this.takenBy = takenBy;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(rideTime);
    }

    public User offeredBy() {
        return vehicle.getOwnedBy();
    }

    private Long generateRideNo() {
        return System.currentTimeMillis() % Long.MAX_VALUE;
    }
}
