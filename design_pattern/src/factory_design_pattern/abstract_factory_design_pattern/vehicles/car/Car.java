package factory_design_pattern.abstract_factory_design_pattern.vehicles.car;

import
factory_design_pattern.abstract_factory_design_pattern.IVehicle;

public interface Car extends IVehicle {
    String getName();
    void setName(String name);
}
