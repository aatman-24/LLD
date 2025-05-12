package 
flyweight_design_pattern;

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

public class FlyweightDesignClient {

    public static void run() {

        IRobot humanoidRobot1 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot1.display(1,2);

        IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot2.display(10,30);

        IRobot roboDog1 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog1.display(2,9);

        IRobot roboDog2 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog2.display(11,19);

    }
}
