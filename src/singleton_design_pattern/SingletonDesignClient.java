package singleton_design_pattern;

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

public class SingletonDesignClient {

    public static void run() {

        System.out.println("SingletonDesignClient");

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        System.out.println(databaseConnection.getDbUsrname());


    }
}
