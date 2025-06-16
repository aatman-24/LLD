package car_rental_system_latest.payment;

public class DefaultPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("Payment is done!");
    }
}
