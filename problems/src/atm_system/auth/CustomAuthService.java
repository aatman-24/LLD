package atm_system.auth;

import atm_system.BankService;
import atm_system.Card;
import atm_system.User;

import java.util.Optional;

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
