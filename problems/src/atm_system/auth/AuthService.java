package atm_system.auth;

import atm_system.Card;
import atm_system.User;

public interface AuthService {
    public User isCardAuthorized(Card card, Integer cardPIN);
}
