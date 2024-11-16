package 
strategy_design_pattern.WithPattern;

public class PassengerVehicle extends Vehicle {
    public PassengerVehicle() {
        super(new NormalDriveVehicle());
    }
}
