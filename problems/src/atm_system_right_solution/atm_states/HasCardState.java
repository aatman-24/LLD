package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;

import java.util.Objects;

public class HasCardState extends AtmMachineState {

    public HasCardState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void authenticateCard(Card card, Integer pin) {
        boolean equals = Objects.equals(card.getPin(), pin);
        if(equals) {
            System.out.println("Pin is correct!!");
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getSelectOperationState());
        }
        else {
            System.out.println("Incorrect PIN, try again!!");
        }
    }
}
