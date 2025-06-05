package atm_system_right_solution.withdraw_processor;

import atm_system_right_solution.AtmMachine;
import atm_system_right_solution.CardDispenser;

import static java.lang.Math.min;

public class TwoThousandNoteProcessor extends CashWithdrawProcessor {

    public TwoThousandNoteProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(AtmMachine atmMachine, Integer amount) {
        Integer noOfTwoThousandNoteRequired = (int) Math.floor(amount*1.0/2000);
        CardDispenser cardDispenser = atmMachine.getCardDispenser();
        Integer noOfTwoThousandCanBeDeduct = min(noOfTwoThousandNoteRequired, cardDispenser.getTwoThousandNoteCount());
        cardDispenser.dispense(CurrencyNote.TWO_THOUSAND, noOfTwoThousandCanBeDeduct);
        this.getNextProcessor().withdraw(atmMachine, amount - noOfTwoThousandCanBeDeduct * 2000);
    }
}
