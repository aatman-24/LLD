package car_rental_system_latest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class VehicleInventoryManagement {

    private List<Vehicle> vehicles;

    public VehicleInventoryManagement() {
        vehicles = new ArrayList<>(10);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // public void removeVehicle(Vehicle vehicle) {}
}
