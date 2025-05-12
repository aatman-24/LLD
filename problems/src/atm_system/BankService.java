package atm_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
