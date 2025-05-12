package car_rental_system;

import java.time.LocalDateTime;

public class Transaction {
    int paymentId;
    Integer amount;
    LocalDateTime transactionDate;
    String transactionType;

    public Transaction() {
    }

    public Transaction(int paymentId, Integer amount, LocalDateTime transactionDate, String transactionType) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
