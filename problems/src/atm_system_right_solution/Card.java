package atm_system_right_solution;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

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
