package factory_design_pattern.abstract_factory_design_pattern;

public class AbstractFactoryDesignClient {

    public static void run() {

        // Case-1:
        IVehicle bmw = VehicleFactory.getVehicle(Constants.CAR, Constants.LUXURY, "BMW");
        System.out.println(bmw);

        // Case-2:
        IVehicle i20 = VehicleFactory.getVehicle(Constants.CAR, Constants.AVERAGE, "i20");
        System.out.println(i20);

        // Case-3:
        IVehicle bumper = VehicleFactory.getVehicle(Constants.TRUCK, Constants.DUMPER, "bumper");
        System.out.println(bumper);

    }

}
