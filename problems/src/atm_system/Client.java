package atm_system;

import atm_system.auth.AccountType;

public class Client {

    public static void main(String[] args) {
        User aatman = BankService.signup("Aatman", 1234567890L, 1234);
        AtmMachine atmMachine = AtmMachine.getInstance();
        atmMachine.verifyCard(aatman.getCard(), 1234);
        atmMachine.deposit(1000);
        atmMachine.withdraw(500);
        System.out.println(atmMachine.getBalance());
    }
}
