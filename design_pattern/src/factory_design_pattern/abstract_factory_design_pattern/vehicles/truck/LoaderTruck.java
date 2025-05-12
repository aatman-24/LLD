package factory_design_pattern.abstract_factory_design_pattern.vehicles.truck;

import
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class LoaderTruck implements Truck {


    String name;
    final String type = Constants.LOADER;

    public LoaderTruck(String name) {
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
        return "LoaderTruck{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
