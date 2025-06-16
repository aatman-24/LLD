package splitwise.balance_sheet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import splitwise.Balance;
import splitwise.user.User;

import java.util.HashMap;

@Data
@ToString
public class UserBalanceSheet {

    private String id;
    private HashMap<User, Balance> userVsBalance;
    private Double totalExpense;
    private Double totalPayment;
    private Double totalOweAmount;
    private Double totalGetBackAmount;

    public UserBalanceSheet(String id) {
        this.id = id;
        userVsBalance = new HashMap<>();
        totalExpense = 0.0;
        totalPayment = 0.0;
        totalOweAmount = 0.0;
        totalGetBackAmount = 0.0;
    }

    public void updateBalanceSheetOfUser(User user, Balance balance) {

        // update the balance
        Balance currentBalance = userVsBalance.get(user);
        if(currentBalance == null) {
            userVsBalance.put(user, balance);
        }
        else {
            currentBalance.setOweAmount(currentBalance.getOweAmount() + balance.getOweAmount());
            currentBalance.setGetBackAmount(currentBalance.getGetBackAmount() + balance.getGetBackAmount());
        }

        // update the total balance
        totalOweAmount += balance.getOweAmount();
        totalGetBackAmount += balance.getGetBackAmount();
    }

    public void addNewExpense(Double amount) {
        totalExpense += amount;
    }

    public void addNewPayment(Double amount) {
        totalPayment += amount;
    }
}
