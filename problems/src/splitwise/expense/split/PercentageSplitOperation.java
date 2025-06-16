package splitwise.expense.split;

import java.util.List;

public class PercentageSplitOperation implements SplitOperation {
    @Override
    public Boolean validateExpense(List<SplitInfo> splitInfoList, Double totalAmount) {
        // TODO: Add logic based on percentage wise
        return true;
    }
}
