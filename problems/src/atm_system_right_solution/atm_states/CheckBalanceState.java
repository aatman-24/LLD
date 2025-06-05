package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;

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
