package atm_system_right_solution.atm_states;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.Card;
import atm_system_right_solution.withdraw_processor.CashWithdrawProcessor;
import atm_system_right_solution.withdraw_processor.FiveHundredNoteProcessor;
import atm_system_right_solution.withdraw_processor.OneHundredNoteProcessor;
import atm_system_right_solution.withdraw_processor.TwoThousandNoteProcessor;

public class WithdrawState extends  AtmMachineState {

    public WithdrawState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void cashWithdraw(Card card, Integer amount) {

        if(card.getAccount().getBalance() < amount) {
            System.out.println("Insufficient Balance");
            exit();
        }

        Integer currentBalance = this.getAtmMachine().getCardDispenser().getCurrentBalance();
        if(currentBalance < amount) {
            System.out.println("Out of fund!!");
            exit();
        }

        CashWithdrawProcessor cashWithdrawProcessor = new TwoThousandNoteProcessor(new FiveHundredNoteProcessor(new OneHundredNoteProcessor(null)));
        cashWithdrawProcessor.withdraw(getAtmMachine(), amount);
        exit();
    }
}
