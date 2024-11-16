package 
factory_design_pattern.abstract_factory_design_pattern.vehicles.truck;
import 
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class TruckFactory {

    public static Truck getTruck(String type, String name) {
        return switch (type) {
            case Constants.LOADER -> new LoaderTruck(name);
            case Constants.DUMPER -> new DumperTruck(name);
            default -> new DumperTruck(name);
        };

    }
}
