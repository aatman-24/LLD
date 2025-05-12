package factory_design_pattern.abstract_factory_design_pattern.vehicles.car;

import
factory_design_pattern.abstract_factory_design_pattern.Constants;

public class CarFactory {

    public static Car getCar(String type, String carName) {
        return switch (type) {
            case Constants.LUXURY -> new LuxuryCarImpl(carName);
            case Constants.AVERAGE -> new AverageCarImpl(carName);
            default -> new AverageCarImpl(carName);
        };
    }
}
