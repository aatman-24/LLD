package 
state_design_pattern.states;

import 
state_design_pattern.VendingMachine;

public class IdealState implements State{

    VendingMachine vendingMachine;

    public IdealState(VendingMachine vendingMachine) {
        System.out.println("**STATE**: IdealState");
        this.vendingMachine = vendingMachine;
        this.vendingMachine.setCurrentCoin(0);
    }

    @Override
    public State insertCoin(int coinValue) {
        System.out.println("You inserted, " + coinValue);
        this.vendingMachine.setCurrentCoin(coinValue);
        return new HasCoinState(vendingMachine);
    }

    @Override
    public State coinInsertionDone() {
        System.out.println("Please insert coins first");
        return this;
    }

    @Override
    public State insertCode(String code) {
        System.out.println("Please insert coins first");
        return this;
    }

    @Override
    public State hitCancel() {
        System.out.println("Refreshed machine, now you are in ideal state");
        return this;
    }

    @Override
    public State dispenseSuccess() {
        System.out.println("Please insert coins first");
        return this;
    }
}
