package atm_system_right_solution.withdraw_processor;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.CardDispenser;

import static java.lang.Math.min;

public class FiveHundredNoteProcessor extends CashWithdrawProcessor{

    public FiveHundredNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        int noOfFiveHundredNoteRequired = (int) Math.floor(amount*1.0/500);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        int noOfNoteCanBeDeduct = min(noOfFiveHundredNoteRequired, cardDispenser.getFiveHundredNoteCount());
        cardDispenser.dispense(CurrencyNote.FIVE_HUNDRED, noOfNoteCanBeDeduct);
        this.getNextProcessor().withdraw(atmMachine, amount - (noOfNoteCanBeDeduct * 500));
    }
}
