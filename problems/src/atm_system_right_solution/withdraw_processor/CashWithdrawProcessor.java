package atm_system_right_solution.withdraw_processor;

import atm_system_right_solution.AtmMachine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CashWithdrawProcessor {

    private CashWithdrawProcessor nextProcessor;

    public CashWithdrawProcessor(CashWithdrawProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void withdraw(AtmMachine atmMachine, Integer amount);
}
