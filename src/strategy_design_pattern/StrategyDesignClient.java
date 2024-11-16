package strategy_design_pattern;

import strategy_design_pattern.WithPattern.OffRoadVehicle;
import strategy_design_pattern.WithPattern.PassengerVehicle;
import strategy_design_pattern.WithPattern.SportsVehicle;
import strategy_design_pattern.WithPattern.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class StrategyDesignClient {

    public static void run() {

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new SportsVehicle());
        vehicleList.add(new PassengerVehicle());
        vehicleList.add(new OffRoadVehicle());

        for(Vehicle vehicle: vehicleList) {
            vehicle.drive();
        }
    }
}
