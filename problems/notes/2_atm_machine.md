## Design Problems: ATM Machine

## ---

### Requirement

```textile
The ATM system should support basic operations such as balance inquiry, cash withdrawal, and cash deposit.
Users should be able to authenticate themselves using a card and a PIN (Personal Identification Number).
The system should interact with a bank's backend system to validate user accounts and perform transactions.
The ATM should have a cash dispenser to dispense cash to users.
The system should handle concurrent access and ensure data consistency.
The ATM should have a user-friendly interface for users to interact with.
```

### <u>Identify Flow</u>

```tex
User sign up with bank => create a user, account, card for that user. 
Card have 12 digit number, with 4 digit pin which is set by user
when user enter into ATM machine and swipe the card, first request
goes to the AuthService for validation.....if we get approval....
we allow all operations.... showBalance(), withdraw().... 
during withdraw of cash... CashDispense comes into picture for dispense the cash. 
```

### <u>Entity LookUp</u>

```tex
User
Account
Card
ATMMachine
BankService: Goal is to manage all users
AuthService(I): Goal is to authenticate the card when request comes
CashDispenser: Goal is to dispense the Cash
```

#### <u>Relationship Lookup</u>

```tex
BankService -> User (1 -> N)
User - Account - Card (1 - 1 - 1)
ATMMachine - CashDispenser
```

#### <u>Applying SOLID Principle | Design Patterns</u>

```tex
- ATMMachine (Singleton)
- AuthService we can take as Stratgey Design Pattern.... Ultimatelly what I want is in future we can replace that logic without minimal changes
```

#### <u>Class Diagram</u>

```tex
User
- id: Integer
- name: String
- mobileNumber: Integer
- account: Account
- card: Card

Account
- accountNumber: Integer
- holderName: String
- balance: Integer

Card
- cardNumber: Integer
- pin: Integer

ATMMachine
- atmMachine: ATMMachine
- currentUser: User
- dispenser: CashDispenser
--
+ authenticateCard(): User
+ showBalance(): void
+ withdraw(): void

BankService
- users: List<User>
--
+ signup(): User
+ getUserUsingCardNumber(): User

AuthService(I)
+ verifyCard(): User

CustomAuthService
+ verifyCard(): User

CashDispenser
+ dispenseCash(): Integer
```

#### <u>Code</u>

<u>auth-package</u>

```java
public enum AccountType {
    SAVING,
    CURRENT
}


public interface AuthService {
    public User isCardAuthorized(Card card, Integer cardPIN);
}


public class CustomAuthService implements AuthService {

    @Override
    public User isCardAuthorized(Card card, Integer cardPIN) {
        Optional<User> optinalUser = BankService.getUserByCardNumber(card.getCardNumber());
        if(optinalUser.isPresent()) {
            User user = optinalUser.get();
            if(user.getCard().getCardPIN().equals(cardPIN)) {
                return user;
            }
        }
        return null;
    }
}
```

<u>other-classes</u>

```java
public class User {

    private static int count = 0;

    private int id;
    private String name;
    private Long mobileNumber;
    private Account account;
    private Card card;

    public User(String name, Long mobileNumber, Integer cardPin) {
        this.id = ++count;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.account = new Account(name, AccountType.SAVING);
        this.card = new Card(name, "Savings");
        this.card.setCardPIN(cardPin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Account getAccount() {
        return account;
    }

    public Card getCard() {
        return card;
    }
}


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


public class Card {

    private static int counter = 0;
    private int cardId;
    private Long cardNumber;
    private String cardHolderName;
    private String cardType;
    private Integer cardPIN;

    public Card(String cardHolderName, String cardType) {
        this.cardId = ++counter;
        this.cardNumber = generateCardNumber();
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
    }

    private Long generateCardNumber() {
        Random random = new Random();
        return random.nextLong(1000000000L, 9999999999L);
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(Integer cardPIN) {
        this.cardPIN = cardPIN;
    }
}
```

```java
public class BankService {

    private static List<User> userList = new ArrayList<>(10);
    private static BankService bankService = null;

    private BankService() {
    }

    public static BankService getInstance() {
        if(bankService == null) {
            bankService = new BankService();
        }
        return bankService;
    }

    public static User signup(String name, Long mobileNumber, Integer cardPin) {
        User user = new User(name, mobileNumber, cardPin);
        userList.add(user);
        return user;
    }

    public static Optional<User> getUserByCardNumber(Long cardNumber) {
        return userList.stream().filter(user -> user.getCard().getCardNumber().equals(cardNumber)).findFirst();
    }
}
```

```java
public class AtmMachine {

    private static AtmMachine atmMachine = null;
    private User currentUser;
    private AuthService authService;
    private CashDispenser cashDispenser;

    private AtmMachine() {
        authService = new CustomAuthService();
        cashDispenser = new CashDispenser();
    }

    public static AtmMachine getInstance() {
        if(atmMachine == null) {
            atmMachine = new AtmMachine();
        }
        return atmMachine;
    }

    public void verifyCard(Card card, Integer cardPIN) {
        currentUser = authService.isCardAuthorized(card, cardPIN);
        if(currentUser == null) {
            System.out.println("Incorrect card number or PIN");
        }
    }

    public Integer getBalance() {
        if(isUserLoggedIn()) {
            return currentUser.getAccount().getBalance();
        }
        return null;
    }

    // We can move this method to BankService as well... if required
    public void deposit(Integer amount) {
        if(isUserLoggedIn()) {
            currentUser.getAccount().deposit(amount);
        }
    }

    public void withdraw(Integer amount) {
        if(isUserLoggedIn()) {
            currentUser.getAccount().withdraw(amount);
            cashDispenser.dispenseCash(amount);
        }
    }

    private boolean isUserLoggedIn() {
        if(currentUser == null) {
            System.out.println("You are not logged in");
            return false;
        }
        return true;
    }
}


public class CashDispenser {
    public void dispenseCash(Integer amount) {
        System.out.println("Dispensing cash: " + amount);
    }
}
```

```java
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
```

```java
public enum TransactionType {
    DEBIT,
    CREDIT
}
```
