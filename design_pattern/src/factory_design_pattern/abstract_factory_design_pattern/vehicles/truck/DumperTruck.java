package factory_design_pattern.abstract_factory_design_pattern.vehicles.truck;

import
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class DumperTruck implements Truck {

    String name;
    final String type = Constants.DUMPER;

    public DumperTruck(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DumperTruck{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
