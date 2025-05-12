package atm_system;

import atm_system.auth.AccountType;

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
