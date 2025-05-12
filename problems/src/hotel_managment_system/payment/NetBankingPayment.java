package hotel_managment_system.payment;

public class NetBankingPayment implements Payment{
    @Override
    public boolean pay(int amount) {
        System.out.println("Payment accepted via NetBanking: $" + amount);
        return true;
    }
}
