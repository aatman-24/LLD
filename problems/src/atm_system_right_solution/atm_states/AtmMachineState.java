package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public abstract class AtmMachineState {

    private AtmMachine atmMachine;

    public AtmMachineState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    public void insertCard(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void authenticateCard(Card card, Integer pin) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void selectOperation(Card card, OpeartionType opType) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void cashWithdraw(Card card, Integer amount) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void checkBalance(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void exit() {
        System.out.println("Please take back your card, Thank you!!");
        this.getAtmMachine().setAtmMachineState(getAtmMachine().getIdealState());
    }
}
