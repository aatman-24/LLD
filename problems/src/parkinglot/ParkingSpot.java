package parkinglot;

public class ParkingSpot {

    private String spotNumber;
    private Vehicle vehicle;
    private boolean isOccupied;
    private VehicleType vehicleType;

    public ParkingSpot(String spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.vehicle = null;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void bookSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        isOccupied = true;
    }

    public void unbookSpot() {
        this.vehicle = null;
        isOccupied = false;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
