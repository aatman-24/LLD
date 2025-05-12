package atm_system;

import atm_system.auth.AuthService;
import atm_system.auth.CustomAuthService;

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
