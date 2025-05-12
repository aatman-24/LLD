package car_rental_system;

import java.time.LocalDateTime;

public class CreditCardPayment implements Payment {

    private static int count = 0;

    public CreditCardPayment() {}

    @Override
    public Transaction pay(Integer amount) {
        System.out.println("Paid $" + amount + " with credit card");
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setPaymentId(count);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType("Credit Card");
        count++;
        return transaction;
    }

    @Override
    public Integer refund(Transaction transaction) {
        // we store transaction somewhere, Here I don't save so we can't verify otherwise we need to verify
        return transaction.getAmount();
    }
}
