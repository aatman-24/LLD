package factory_method_pattern.WithPattern;

public class Truck implements Transport{
    @Override
    public void deliver() {
        System.out.println("Delivering through Truck....");
    }
}