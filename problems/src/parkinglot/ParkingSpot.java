package parkinglot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
