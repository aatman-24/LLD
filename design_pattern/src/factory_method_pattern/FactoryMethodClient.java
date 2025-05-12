package factory_method_pattern;

import
factory_method_pattern.WithPattern.Logistic;
import
factory_method_pattern.WithPattern.RoadLogistic;
import
strategy_design_pattern.WithPattern.OffRoadVehicle;
import
strategy_design_pattern.WithPattern.PassengerVehicle;
import
strategy_design_pattern.WithPattern.SportsVehicle;
import
strategy_design_pattern.WithPattern.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class FactoryMethodClient {

    public static void run() {
        Logistic logistic = new RoadLogistic();
        logistic.doDelivery();
    }
}
