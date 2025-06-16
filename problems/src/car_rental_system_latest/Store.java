package car_rental_system_latest;

import lombok.Data;

import java.util.List;

@Data
public class Store {
    private VehicleInventoryManagement vehicleInventoryManagement;
    private Location location;
    private List<Vehicle> reservedVehicles;
}
