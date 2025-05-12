package ride_sharing;

import java.util.List;

public class User {

    private Long userId;
    private String name;
    private GenderEnum gender;
    private Integer age;
    private List<Vehicle> vehicleList;
    private List<Ride> rideList;

    public User(String name, GenderEnum gender, Integer age) {
        this.userId = generateUserId();
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicleList.remove(vehicle);
    }

    public void offeredRide(Ride ride) {
        rideList.add(ride);
    }

    public void takenRide(Ride ride) {
        rideList.add(ride);
    }

    private Long generateUserId() {
        return System.currentTimeMillis() % Long.MAX_VALUE;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }
}
