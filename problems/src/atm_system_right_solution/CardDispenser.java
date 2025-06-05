package atm_system_right_solution;

import atm_system_right_solution.withdraw_processor.CurrencyNote;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class CardDispenser {

    private Integer twoThousandNoteCount;
    private Integer fiveHundredNoteCount;
    private Integer oneHundredNoteCount;

    public CardDispenser() {
        oneHundredNoteCount = 10;
        fiveHundredNoteCount = 5;
        twoThousandNoteCount = 2;
    }

    public void dispense(CurrencyNote currencyNote, Integer noOfNote) {
        if(currencyNote == CurrencyNote.TWO_THOUSAND) {
            deductTwoThousandNote(noOfNote);
        }
        else if(currencyNote == CurrencyNote.ONE_HUNDRED) {
            deductFiveHundredNote(noOfNote);
        }
        else {
            deductOneHundredNote(noOfNote);
        }
        System.out.println("Dispense note: " + currencyNote + " : " + noOfNote);
    }

    public Integer getCurrentBalance() {
        return oneHundredNoteCount * 100 + fiveHundredNoteCount * 500 + twoThousandNoteCount * 2000;
    }

    private void deductTwoThousandNote(Integer noOfNote) {
        this.twoThousandNoteCount -= noOfNote;
    }

    private void deductFiveHundredNote(Integer noOfNote) {
        this.fiveHundredNoteCount -= noOfNote;
    }

    private void deductOneHundredNote(Integer noOfNote) {
        this.oneHundredNoteCount -= noOfNote;
    }
}
