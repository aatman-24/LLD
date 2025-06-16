package splitwise.expense.split;

public class SplitOperationFactory {

    public static SplitOperation getSplitOperation(SplitType splitType) {
        if(splitType == SplitType.EQUAL) {
            return new EqualSplitOperation();
        }
        if(splitType == SplitType.EXACT) {
            return new UnEqualSplitOperation();
        }
        if(splitType == SplitType.PERCENT) {
            return new PercentageSplitOperation();
        }
        return null;
    }

}
