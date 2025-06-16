package splitwise.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import splitwise.expense.split.SplitInfo;
import splitwise.expense.split.SplitType;
import splitwise.user.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private String id;
    private String description;
    private Double amount;
    private User paidBy;
    private SplitType splitType;
    private List<SplitInfo> splitInfos;
}
