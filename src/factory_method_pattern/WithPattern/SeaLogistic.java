package factory_method_pattern.WithPattern;

public class SeaLogistic extends Logistic {
    @Override
    Transport createTrasport() {
        return new Ship();
    }
}
