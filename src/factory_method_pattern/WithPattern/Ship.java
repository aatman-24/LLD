package factory_method_pattern.WithPattern;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering through Truck....");
    }
}
