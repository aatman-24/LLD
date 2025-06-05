## Design Problems: ATM Machine System (Right Solution)

---

### Requirement

```
The ATM system should support basic operations such as balance inquiry, cash withdrawal, and cash deposit.
Users should be able to authenticate themselves using a card and a PIN (Personal Identification Number).
The system should interact with a bank's backend system to validate user accounts and perform transactions.
The ATM should have a cash dispenser to dispense cash to users.
The system should handle concurrent access and ensure data consistency.
The ATM should have a user-friendly interface for users to interact with.
```



### Identify Flow

```tex
User visits the AtmMachineStore, which contains an AtmMachine.

The AtmMachine starts in an Idle state.

The user inserts their card into the machine.

The user enters the ATM PIN.

The ATM authenticates the card using the BankAuthService.

If authentication is successful:

The machine allows operations like Withdraw and Check Balance.

After the operation, the card is returned, and the machine transitions back to the Idle state.

At any point, if the user presses the Cancel button, the transaction is canceled, the card is returned, and the machine returns to the Idle state.

If authentication fails, the transaction is rejected, the card is returned, and the machine goes back to the Idle state.
```

### Entity LookUp

```tex
AtmMachineStore

AtmMachine

User

Card

Account

BankAuthService

CardDispenser
```

### Relationship Lookup

```tex
AtmMachineStore → User

AtmMachineStore → AtmMachine

AtmMachine → CardDispenser

User → Card

User → Account
```

### Applying SOLID Principle | Design Patterns

```tex
1. Singleton Pattern
AtmMachine should follow the Singleton pattern to ensure only one instance exists throughout the system.

2. State Design Pattern
Similar to a vending machine, the ATM must behave according to its current state.
Only specific operations should be allowed in each state.

States Flow:

IdleState
  --insertCard()--> HasCardState
    --authenticateCard()--> SelectOperationState
      --selectOperation()--> (WithdrawState | CheckBalanceState)
        --(actionPerformed())--> IdleState
Operations supported per state:

IdleState: Accepts card
HasCardState: Authenticates card
SelectOperationState: Accepts operation input (withdraw/check balance)
WithdrawState: Performs withdrawal
CheckBalanceState: Displays balance

All states may handle exit() or cancel() transitions back to IdleState

3. Chain of Responsibility Pattern: Cash Dispensing
The CardDispenser handles cash dispensing in a chain-like structure:

Scenario:
User withdraws ₹2700

First, TwoThousandNoteHandler tries to dispense as much as possible.

Then passes the remaining to FiveHundredNoteHandler

Lastly, OneHundredNoteHandler handles the final amount.

Each handler:

Checks available notes
Dispenses appropriate quantity
Updates inventory
Passes remaining amount to the next handler in the chain
This pattern helps keep the cash dispensing logic modular and flexible.

Handlers:

TwoThousandNoteHandler
FiveHundredNoteHandler
OneHundredNoteHandler

Note: Confirm whether the interviewer expects you to go in-depth on this.
```

### Updated Entities

```tex
AtmMachineStore

AtmMachine

AtmMachineState: IdleState, HasCardState, SelectOperationState, WithdrawState, CheckBalanceState

User

Card

Account

BankAuthService

CardDispenser

CashWithdrawProcessor

TwoThousandNoteProcessor

FiveHundredNoteProcessor

OneHundredNoteProcessor
```

### Updated Relationships

```tex
AtmMachineStore → User

AtmMachineStore → AtmMachine

AtmMachine → CardDispenser

AtmMachine → AtmMachineState

User → Card

User → Account

Card → Account

CardDispenser → CashWithdrawProcessor
```

### Class Diagram

```tex
AtmMachineStore
- atmMachine: ATM
- user: user

AtmMachine
- atmMachineCurrentState: AtmMachineState
- cardDispenser: CardDispenser
- twoThousandNotes: Integer
- fiveHundredNotes: Integer
- oneHundredNotes: Integer

AtmMachineState
- atmMachine: AtmMachine
---
- insertCard(Card card): void
- authenticateCard(Card card, Integer pin): void
- selectOperation(Card card, OpeartionType opType): void
- cashWithdraw(Card card, Integer amount): void
- checkBalance(Card card): void
- exit(): void

IdealState
- insertCard(Card card): void

HasCardState
- authenticateCard(Card card, Integer pin): void
- exit()

SelectOperationState
- selectOperation(Card card, OpeartionType opType): void
- exit()

WithdrawState
- cashWithdraw(Card card, Integer amount): void
- exit()

CheckBalanceState
- checkBalance(Card card): void
- exit()

User
- card: Card
- account: Account

Card
- cardNumber: Long
- account: Account
- pin: Integer

Account
- accountNumber: Long
- balance: Integer

BankAuthService
- authenticateCard(): Boolean

CardDispenser
- twoThousandNoteCount: Integer
- fiveHundredNoteCount: Integer
- oneHundredNoteCount: Integer
--
+ dispense(CurrencyNote currencyNote, Integer noOfNote): void
+ getCurrentBalance(): Integer
- deductTwoThousandNote(Integer noOfNote): void
- deductFiveHundredNote(Integer noOfNote): void
- deductOneHundredNote(Integer noOfNote): void

CurrencyNote
- TWO_THOUSAND
- FIVE_HUNDRED
- ONE_HUNDRED

CashWithdrawProcessor
- nextProcessor: CashWithdrawProcessor
--
- withdraw(AtmMachine atmMachine, Integer Amount): void

TwoThousandNoteProcessor -> FiveHundredNoteProcessor -> OneHundredNoteProcessor -> NULL
```

### Source Code

```java
@Data
@Getter
@Setter
@ToString
public class Account {

    private Long accountNumber;
    private Integer balance;
    private static Long counter = 0L;

    public Account() {
        accountNumber = ++counter;
        balance = 10000;
    }
}


@Data
@Getter
@Setter
@ToString
public class Card {

    private Long cardNumber;
    private Account account;
    private Integer pin;

    public Card(Account account, Integer pin) {
        this.cardNumber = getCardNumber();
        this.account = account;
        this.pin = pin;
    }

    private static Long getCardNumber() {
        Random random = new Random(10);
        return random.nextLong(10000000, 999999999);
    }
}


@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    private String userName;
    private Account account;
    private Card card;

    public User(String userName) {
        this.userName = userName;
        this.account = new Account();
        this.card = new Card(this.account, 4444);
    }
}

public class BankAuthService {

    public Boolean authenticateCard() {
        System.out.println("user authenticated");
        return true;
    }
}


public class AtmMachineStore {
}
```



```java
#atm_states_package


@Data
@Getter
@Setter
@NoArgsConstructor
public abstract class AtmMachineState {

    private AtmMachine atmMachine;

    public AtmMachineState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    public void insertCard(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void authenticateCard(Card card, Integer pin) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void selectOperation(Card card, OpeartionType opType) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void cashWithdraw(Card card, Integer amount) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void checkBalance(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void exit() {
        System.out.println("Please take back your card, Thank you!!");
        this.getAtmMachine().setAtmMachineState(getAtmMachine().getIdealState());
    }
}



public class IdealState extends AtmMachineState {

    public IdealState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has been inserted!!");
        this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getHasCardState());
    }
}


public class HasCardState extends AtmMachineState {

    public HasCardState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void authenticateCard(Card card, Integer pin) {
        boolean equals = Objects.equals(card.getPin(), pin);
        if(equals) {
            System.out.println("Pin is correct!!");
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getSelectOperationState());
        }
        else {
            System.out.println("Incorrect PIN, try again!!");
        }
    }
}

public class SelectOperationState extends AtmMachineState {

    public SelectOperationState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void selectOperation(Card card, OpeartionType opType) {


        if(OpeartionType.WITHDRAW.equals(opType)) {
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getWithdrawState());
        }

        else if(OpeartionType.CHECK_BALANCE.equals(opType)) {
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getCheckBalanceState());
        }

        else {
            System.out.println("Operation type not found!!");
        }
    }
}


public class WithdrawState extends  AtmMachineState {

    public WithdrawState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void cashWithdraw(Card card, Integer amount) {

        if(card.getAccount().getBalance() < amount) {
            System.out.println("Insufficient Balance");
            exit();
        }

        Integer currentBalance = this.getAtmMachine().getCardDispenser().getCurrentBalance();
        if(currentBalance < amount) {
            System.out.println("Out of fund!!");
            exit();
        }

        CashWithdrawProcessor cashWithdrawProcessor = new TwoThousandNoteProcessor(new FiveHundredNoteProcessor(new OneHundredNoteProcessor(null)));
        cashWithdrawProcessor.withdraw(getAtmMachine(), amount);
        exit();
    }
}


public class CheckBalanceState extends AtmMachineState {

    public CheckBalanceState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void checkBalance(Card card) {
        Integer balance = card.getAccount().getBalance();
        System.out.println("Balance: " + balance);
        exit();
    }
}


public enum OpeartionType {
    WITHDRAW,
    CHECK_BALANCE
}

```



```java
#withdraw_processor


@Getter
@Setter
public abstract class CashWithdrawProcessor {

    private CashWithdrawProcessor nextProcessor;

    public CashWithdrawProcessor(CashWithdrawProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void withdraw(AtmMachine atmMachine, Integer amount);
}


public class TwoThousandNoteProcessor extends CashWithdrawProcessor {

    public TwoThousandNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        Integer noOfTwoThousandNoteRequired = (int) Math.floor(amount*1.0/2000);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        Integer noOfTwoThousandCanBeDeduct = min(noOfTwoThousandNoteRequired, cardDispenser.getTwoThousandNoteCount());
        cardDispenser.dispense(CurrencyNote.TWO_THOUSAND, noOfTwoThousandCanBeDeduct);
        this.getNextProcessor().withdraw(atmMachine, amount - noOfTwoThousandCanBeDeduct * 2000);
    }
}


public class FiveHundredNoteProcessor extends CashWithdrawProcessor{

    public FiveHundredNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        int noOfFiveHundredNoteRequired = (int) Math.floor(amount*1.0/500);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        int noOfNoteCanBeDeduct = min(noOfFiveHundredNoteRequired, cardDispenser.getFiveHundredNoteCount());
        cardDispenser.dispense(CurrencyNote.FIVE_HUNDRED, noOfNoteCanBeDeduct);
        this.getNextProcessor().withdraw(atmMachine, amount - (noOfNoteCanBeDeduct * 500));
    }
}



public class OneHundredNoteProcessor extends CashWithdrawProcessor {

    public OneHundredNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        int noOfOneHundredNoteRequired = (int) Math.floor(amount*1.0/100);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        Integer noOfNoteCanBeDeduct = min(noOfOneHundredNoteRequired, cardDispenser.getFiveHundredNoteCount());
        cardDispenser.dispense(CurrencyNote.ONE_HUNDRED, noOfNoteCanBeDeduct);
    }
}


@ToString
public enum CurrencyNote {
    TWO_THOUSAND,
    FIVE_HUNDRED,
    ONE_HUNDRED;
}

```



```java

@Getter
public class AtmMachine {

    // All are states of ATM
    @Setter
    private AtmMachineState atmMachineState;

    private final AtmMachineState idealState;

    private final AtmMachineState hasCardState;

    private final AtmMachineState selectOperationState;

    private final AtmMachineState withdrawState;

    private final AtmMachineState checkBalanceState;

    // Card Dispenser
    private final CardDispenser cardDispenser;

    public AtmMachine() {
        idealState = new IdealState(this);
        hasCardState = new HasCardState(this);
        selectOperationState = new SelectOperationState(this);
        withdrawState = new WithdrawState(this);
        checkBalanceState = new CheckBalanceState(this);
        atmMachineState = idealState;
        cardDispenser = new CardDispenser();
    }

    public void insertCard(Card card) {
        atmMachineState.insertCard(card);
    }

    public void authenticateCard(Card card, Integer pin) {
        atmMachineState.authenticateCard(card, pin);
    }

    public void selectOperation(Card card, OpeartionType opType) {
        atmMachineState.selectOperation(card, opType);
    }

    public void cashWithdraw(Card card, Integer amount) {
        atmMachineState.cashWithdraw(card, amount);
    }

    public void checkBalance(Card card) {
        atmMachineState.checkBalance(card);
    }

    public void exit() {
        atmMachineState.exit();
    }
}

public class Driver {

    public static void main(String[] args) {

        // Happy Flow
        User user = new User("aatman");
        Card card = user.getCard();
        AtmMachine atmMachine = new AtmMachine();
        atmMachine.insertCard(card);
        atmMachine.authenticateCard(card, 3333);
        atmMachine.authenticateCard(card, 4444);
        atmMachine.selectOperation(card, OpeartionType.WITHDRAW);
        atmMachine.cashWithdraw(card, 3200);

    }
}

```
