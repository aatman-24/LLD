package factory_method_pattern.WithPattern;

abstract public class Logistic {

    public Logistic() {
    }

    public void doDelivery() {
        // Client code invoking deliver() method.
        Transport transport = createTrasport();
        transport.deliver();
    }

    abstract Transport createTrasport();
}
