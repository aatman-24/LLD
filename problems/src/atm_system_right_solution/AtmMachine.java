package atm_system_right_solution;

import atm_system_right_solution.atm_states.*;
import lombok.*;

@Getter
public class AtmMachine {

    // All are states of ATM
    @Setter
    private AtmMachineState atmMachineState;

    private final AtmMachineState idealState;

    private final AtmMachineState hasCardState;

    private final AtmMachineState selectOperationState;

    private final AtmMachineState withdrawState;

    private final AtmMachineState checkBalanceState;

    // Card Dispenser
    private final CardDispenser cardDispenser;

    public AtmMachine() {
        idealState = new IdealState(this);
        hasCardState = new HasCardState(this);
        selectOperationState = new SelectOperationState(this);
        withdrawState = new WithdrawState(this);
        checkBalanceState = new CheckBalanceState(this);
        atmMachineState = idealState;
        cardDispenser = new CardDispenser();
    }

    public void insertCard(Card card) {
        atmMachineState.insertCard(card);
    }

    public void authenticateCard(Card card, Integer pin) {
        atmMachineState.authenticateCard(card, pin);
    }

    public void selectOperation(Card card, OpeartionType opType) {
        atmMachineState.selectOperation(card, opType);
    }

    public void cashWithdraw(Card card, Integer amount) {
        atmMachineState.cashWithdraw(card, amount);
    }

    public void checkBalance(Card card) {
        atmMachineState.checkBalance(card);
    }

    public void exit() {
        atmMachineState.exit();
    }
}
