package atm_system;

import java.time.LocalDateTime;

public class Transaction {

    private static int count = 0;

    private int transactionId;
    private Integer amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;

    public Transaction(Integer amount, LocalDateTime transactionDate, TransactionType transactionType) {
        this.transactionId = ++count;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
