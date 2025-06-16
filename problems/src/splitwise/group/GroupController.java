package splitwise.group;

import lombok.Data;
import splitwise.expense.Expense;
import splitwise.expense.split.SplitInfo;
import splitwise.expense.split.SplitType;
import splitwise.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupController {

    private List<Group> groupList;

    public GroupController() {
        this.groupList = new ArrayList<>();
    }

    public void addGroup(Group group) {
        groupList.add(group);
    }

    public  void addMemberToGroup(Group group, User member) {
        group.addMember(member);
    }

    public Expense addExpenseToGroup(Group group, String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos) {
        Expense expense = group.addExpense(expenseId, description, splitType, totalAmount, paidBy, splitInfos);
        return expense;
    }
}
