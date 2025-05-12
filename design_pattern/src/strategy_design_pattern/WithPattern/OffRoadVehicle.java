package 
strategy_design_pattern.WithPattern;

public class OffRoadVehicle extends Vehicle {
    public OffRoadVehicle() {
        super(new FastDriveVehicle());
    }
}
