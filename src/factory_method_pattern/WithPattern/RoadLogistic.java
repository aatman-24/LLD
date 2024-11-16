package factory_method_pattern.WithPattern;

public class RoadLogistic extends Logistic {
    @Override
    Transport createTrasport() {
        return new Truck();
    }
}
