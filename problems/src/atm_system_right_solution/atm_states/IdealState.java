package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;

public class IdealState extends AtmMachineState {

    public IdealState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has been inserted!!");
        this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getHasCardState());
    }
}
