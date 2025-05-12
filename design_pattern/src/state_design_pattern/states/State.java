package 
state_design_pattern.states;

public interface State {
    State insertCoin(int coinValue);
    State coinInsertionDone();
    State insertCode(String code);
    State hitCancel();
    State dispenseSuccess();
}
