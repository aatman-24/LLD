package 
factory_design_pattern.abstract_factory_design_pattern.vehicles.truck;

import 
factory_design_pattern.abstract_factory_design_pattern.IVehicle;

public interface Truck extends IVehicle {
    String getName();
    void setName(String name);
}
