package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;

public class SelectOperationState extends AtmMachineState {

    public SelectOperationState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void selectOperation(Card card, OpeartionType opType) {


        if(OpeartionType.WITHDRAW.equals(opType)) {
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getWithdrawState());
        }

        else if(OpeartionType.CHECK_BALANCE.equals(opType)) {
            this.getAtmMachine().setAtmMachineState(this.getAtmMachine().getCheckBalanceState());
        }

        else {
            System.out.println("Operation type not found!!");
        }
    }
}
