package hotel_managment_system.payment;

public class CreditCardPayment implements Payment {
    @Override
    public boolean pay(int amount) {
        System.out.println("Payment accepted via CreditCard: $" + amount);
        return true;
    }
}
