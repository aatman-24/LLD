package atm_system_right_solution.withdraw_processor;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.CardDispenser;

import static java.lang.Math.min;

public class OneHundredNoteProcessor extends CashWithdrawProcessor {

    public OneHundredNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        int noOfOneHundredNoteRequired = (int) Math.floor(amount*1.0/100);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        Integer noOfNoteCanBeDeduct = min(noOfOneHundredNoteRequired, cardDispenser.getFiveHundredNoteCount());
        cardDispenser.dispense(CurrencyNote.ONE_HUNDRED, noOfNoteCanBeDeduct);
    }
}
