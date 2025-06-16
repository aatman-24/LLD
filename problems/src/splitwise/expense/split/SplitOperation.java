package splitwise.expense.split;

import java.util.List;

public interface SplitOperation {
    Boolean validateExpense(List<SplitInfo> splitInfoList, Double totalAmount);
}
