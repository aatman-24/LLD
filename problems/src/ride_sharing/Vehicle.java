package ride_sharing;

public class Vehicle {

    private String vehicleNum;
    private VehicleType vehicleType;
    private User ownedBy;
    private String nameOfCar;

    public Vehicle(String vehicleNum, VehicleType vehicleType, User ownedBy, String nameOfCar) {
        this.vehicleNum = vehicleNum;
        this.vehicleType = vehicleType;
        this.ownedBy = ownedBy;
        this.nameOfCar = nameOfCar;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public User getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(User ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getNameOfCar() {
        return nameOfCar;
    }

    public void setNameOfCar(String nameOfCar) {
        this.nameOfCar = nameOfCar;
    }
}
