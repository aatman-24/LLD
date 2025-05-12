package atm_system;

import java.util.Random;

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
