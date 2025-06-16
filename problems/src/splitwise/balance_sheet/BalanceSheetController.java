package splitwise.balance_sheet;

import splitwise.Balance;
import splitwise.expense.Expense;
import splitwise.expense.split.SplitInfo;
import splitwise.expense.split.SplitOperation;
import splitwise.expense.split.SplitOperationFactory;
import splitwise.expense.split.SplitType;
import splitwise.user.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {

    public Expense addExpense(String expenseId, String description, SplitType splitType, Double totalAmount, User paidBy, List<SplitInfo> splitInfos) {
        SplitOperation splitOperation = SplitOperationFactory.getSplitOperation(splitType);
        if (splitOperation != null) {
            Boolean validated = splitOperation.validateExpense(splitInfos, totalAmount);
            if(validated) {

                Expense expense = new Expense(expenseId, description, totalAmount, paidBy, splitType, splitInfos);

                // we update the balance sheet for both users
                // paidUser, we credit the balance
                // ownedUser, we debit the balance
                for(SplitInfo splitInfo : splitInfos) {

                    User owedUser = splitInfo.getUser();

                    if(owedUser.equals(paidBy))
                        continue;

                    Double splitAmount = splitInfo.getAmount();
                    Balance oweBalance = new Balance(splitAmount, 0.0);
                    Balance creditBalance = new Balance(0.0, splitAmount);

                    try {
                        // paidUser -> owedUser
                        paidBy.getUserBalanceSheet().updateBalanceSheetOfUser(owedUser, creditBalance);

                        // oweUser -> paidUser
                        owedUser.getUserBalanceSheet().updateBalanceSheetOfUser(paidBy, oweBalance);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                // For each user we add new expense
                for(SplitInfo splitInfo : splitInfos) {
                    User owedUser = splitInfo.getUser();
                    Double splitAmount = splitInfo.getAmount();
                    owedUser.getUserBalanceSheet().addNewExpense(splitAmount);
                }

                // total user paid total payment
                paidBy.getUserBalanceSheet().addNewPayment(totalAmount);

                return expense;
            }
            else {
                System.out.println("Invalid split!!");
            }
        }
        return null;
    }

    public void showBalanceSheetOfUser(User user){

        System.out.println("---------------------------------------");

        System.out.println("Balance sheet of user : " + user.getId());

        UserBalanceSheet userExpenseBalanceSheet =  user.getUserBalanceSheet();

        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalExpense());
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalGetBackAmount());
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalOweAmount());
        System.out.println("TotalPaymnetMade: " + userExpenseBalanceSheet.getTotalPayment());
        for(Map.Entry<User, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()){

            String userID = entry.getKey().getId();
            Balance balance = entry.getValue();

            System.out.println("userID:" + userID + " YouGetBack:" + balance.getGetBackAmount() + " YouOwe:" + balance.getOweAmount());
        }

        System.out.println("---------------------------------------");

    }
}
