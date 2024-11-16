package 
strategy_design_pattern.WithPattern;

public class NormalDriveVehicle implements DriveVehicle {
    @Override
    public void drive() {
        System.out.println("DRIVE AT: 70");
    }
}
