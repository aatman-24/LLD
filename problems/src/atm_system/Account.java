package atm_system;

import atm_system.auth.AccountType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {

    private Long accountNumber;
    private String accountName;
    private AccountType accountType;
    private Integer balance;
    private List<Transaction> transaction;

    public Account(String accountName, AccountType accountType) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = 0;
        this.accountNumber = generateAccountNumber();
        this.transaction = new ArrayList<>(10);
    }

    public Integer getBalance() {
        return balance;
    }

    public void deposit(Integer amount) {
        if(amount > 0) {
            balance += amount;
            Transaction t = new Transaction(amount, LocalDateTime.now(), TransactionType.CREDIT);
            transaction.add(t);
        }
    }

    public void withdraw(Integer amount) {
        if(balance >= amount) {
            balance -= amount;
            Transaction t = new Transaction(amount, LocalDateTime.now(), TransactionType.DEBIT);
            transaction.add(t);
            System.out.println("Withdrawal successful: " + amount);
        }
        else {
            System.out.println("Insufficient balance");
        }
    }

    private Long generateAccountNumber() {
        Random random = new Random();
        return random.nextLong(1000000000L, 9999999999L);
    }
}
