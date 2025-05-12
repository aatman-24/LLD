package factory_design_pattern.abstract_factory_design_pattern;

import
factory_design_pattern.abstract_factory_design_pattern.vehicles.car.CarFactory;
import
factory_design_pattern.abstract_factory_design_pattern.vehicles.truck.TruckFactory;

public class VehicleFactory {

    public static IVehicle getVehicle(String vehicle_type, String facility_type, String name) {

        return switch (vehicle_type) {
            case Constants.CAR -> CarFactory.getCar(facility_type, name);
            case Constants.TRUCK -> TruckFactory.getTruck(facility_type, name);
            default -> TruckFactory.getTruck(facility_type, name);
        };
    }
}
