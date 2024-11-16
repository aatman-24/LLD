package factory_design_pattern.abstract_factory_design_pattern.vehicles.car;

import
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class AverageCarImpl implements Car{

    String name;
    final String type =  Constants.AVERAGE;

    public AverageCarImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AverageCarImpl{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
