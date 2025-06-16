package splitwise.expense.split;

import java.util.List;

public class EqualSplitOperation implements  SplitOperation {

    @Override
    public Boolean validateExpense(List<SplitInfo> splitInfoList, Double totalAmount) {
        double currentTotal = 0;
        double eachSplitAmount = totalAmount / splitInfoList.size();
        for (SplitInfo splitInfo : splitInfoList) {
            if(eachSplitAmount != splitInfo.getAmount()) return false;
            currentTotal += splitInfo.getAmount();
        }
        return currentTotal == totalAmount;
    }
}
