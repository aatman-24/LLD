package 
strategy_design_pattern.WithPattern;

public class SportsVehicle extends Vehicle {

    public SportsVehicle() {
        super(new FastDriveVehicle());
    }
}
