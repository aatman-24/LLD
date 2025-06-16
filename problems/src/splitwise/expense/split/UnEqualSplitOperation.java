package splitwise.expense.split;

import java.util.List;

public class UnEqualSplitOperation implements  SplitOperation{
    @Override
    public Boolean validateExpense(List<SplitInfo> splitInfoList, Double totalAmount) {
        double currentTotal = 0;
        for (SplitInfo splitInfo : splitInfoList) {
            currentTotal += splitInfo.getAmount();
        }
        return currentTotal == totalAmount;
    }
}
