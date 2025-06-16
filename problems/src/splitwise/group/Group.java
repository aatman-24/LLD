package splitwise.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import splitwise.balance_sheet.BalanceSheetController;
import splitwise.expense.Expense;
import splitwise.expense.split.SplitInfo;
import splitwise.expense.split.SplitType;
import splitwise.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {

    private String id;
    private String name;
    private List<User> members;
    private List<Expense> expenseList;
    private BalanceSheetController balanceSheetController;

    public Group(String id, String name, BalanceSheetController balanceSheetController) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenseList = new ArrayList<>();
        this.balanceSheetController = balanceSheetController;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public Expense addExpense(String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos) {
        Expense expense = balanceSheetController.addExpense(expenseId, description, splitType, totalAmount, paidBy, splitInfos);
        if(expense != null) {
            expenseList.add(expense);
        }
        System.out.println("Expense added!");
        return expense;
    }
}
