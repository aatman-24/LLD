package factory_method_pattern.WithoutPattern;

public class Logisitc {

    Truck truck;

    // Can create 1-10 trucks for delivery...and when doDelivery() called.... one of truck goes for delivery...
    public Logisitc() {
        truck = new Truck();
    }

    public void doDelivery() {
        System.out.println("Doing delivery...");
        truck.deliver();
    }
}
