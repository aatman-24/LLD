package 
state_design_pattern;

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

public class StateDesignClient {

    public static void run() {

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(10);
        vendingMachine.insertCoin(5);
        vendingMachine.coinInsertionDone();
        vendingMachine.enterCode("MAZA");
        vendingMachine.enterCode("MAAZA");
        vendingMachine.dispense();

        vendingMachine.insertCoin(25);
        vendingMachine.coinInsertionDone();
        vendingMachine.enterCode("COCK");
        vendingMachine.enterCode("MAAZA");
        vendingMachine.dispense();

    }
}
