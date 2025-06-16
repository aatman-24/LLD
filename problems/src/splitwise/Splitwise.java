package splitwise;

import splitwise.balance_sheet.BalanceSheetController;
import splitwise.expense.Expense;
import splitwise.expense.split.SplitInfo;
import splitwise.expense.split.SplitType;
import splitwise.group.Group;
import splitwise.group.GroupController;
import splitwise.user.User;
import splitwise.user.UserController;

import java.util.List;

public class Splitwise {

    private UserController userController;
    private GroupController groupController;
    private BalanceSheetController balanceSheetController;
    private static Splitwise splitwise = null;

    private Splitwise() {
        userController = new UserController();
        groupController = new GroupController();
        balanceSheetController = new BalanceSheetController();
    }

    public void driver() {

        User u1 = new User("u1", "Aatman");
        User u2 = new User("u2", "Mitul");
        User u3 = new User("u3", "Jay");

        Group group = new Group("g1", "outing", balanceSheetController);
        group.addMember(u1);
        group.addMember(u2);
        group.addMember(u3);

        SplitInfo splitInfo1 = new SplitInfo(u1, 100.00, 0.0);
        SplitInfo splitInfo2 = new SplitInfo(u2, 100.00, 0.0);
        SplitInfo splitInfo3 = new SplitInfo(u3, 100.00, 0.0);
        List<SplitInfo> splitInfo4 = List.of(splitInfo1, splitInfo2, splitInfo3);

        Expense eatIcecream = group.addExpense("expense-1", "eat icecream", SplitType.EQUAL, 300.00, u1, splitInfo4);


//
        SplitInfo splitInfo5 = new SplitInfo(u1, 50.00, 0.0);
        SplitInfo splitInfo6 = new SplitInfo(u2, 200.00, 0.0);
        SplitInfo splitInfo7 = new SplitInfo(u3, 100.00, 0.0);
        List<SplitInfo> splitInfo8 = List.of(splitInfo5, splitInfo6, splitInfo7);
        Expense eatPanipuri = group.addExpense("expense-2", "eat panipuri", SplitType.EXACT, 350.00, u3, splitInfo8);

        balanceSheetController.showBalanceSheetOfUser(u1);
        balanceSheetController.showBalanceSheetOfUser(u2);
        balanceSheetController.showBalanceSheetOfUser(u3);
    }

    public static Splitwise getInstance() {
        if(splitwise == null) {
            splitwise = new Splitwise();
        }
        return splitwise;
    }

}
