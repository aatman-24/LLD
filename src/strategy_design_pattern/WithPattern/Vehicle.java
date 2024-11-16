package 
strategy_design_pattern.WithPattern;

public class Vehicle {

        DriveVehicle driveVehicle;

        public Vehicle(DriveVehicle driveVehicle) {
            this.driveVehicle = driveVehicle;
        }

        public void drive() {
            this.driveVehicle.drive();
        }
}
