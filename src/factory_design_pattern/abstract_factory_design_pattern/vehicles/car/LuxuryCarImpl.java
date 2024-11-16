package factory_design_pattern.abstract_factory_design_pattern.vehicles.car;

import
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class LuxuryCarImpl implements Car{

    String name;
    final String type = Constants.LUXURY;

    public LuxuryCarImpl(String name) {
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
        return "LuxuryCarImpl{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
