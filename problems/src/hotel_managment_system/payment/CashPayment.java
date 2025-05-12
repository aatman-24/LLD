package hotel_managment_system.payment;

public class CashPayment implements Payment {
    @Override
    public boolean pay(int amount) {
        System.out.println("Payment accepted via cash: $" + amount);
        return true;
    }
}
