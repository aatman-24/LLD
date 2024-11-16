package strategy_design_pattern.WithoutPattern;

public class OffRoadVehicle extends Vehicle {

    // same as SportsVehicle.drive()
    @Override
    public void drive() {
        System.out.println("DRIVE AT: 240");
    }
}
