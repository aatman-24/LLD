package splitwise.expense.split;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import splitwise.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitInfo {
    private User user;
    private Double amount;
    private Double percentage;
}
