package atm_system_right_solution;

import atm_system_right_solution.atm_states.OpeartionType;

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
