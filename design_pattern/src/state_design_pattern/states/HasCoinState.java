package 
state_design_pattern.states;

import 
state_design_pattern.VendingMachine;

public class HasCoinState implements State {

    VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public State insertCoin(int coinValue) {
        System.out.println("Again!You inserted, " + coinValue);
        this.vendingMachine.setCurrentCoin(this.vendingMachine.getCurrentCoin() + coinValue);
        return this;
    }

    @Override
    public State coinInsertionDone() {
        System.out.println("Great!! You have locked coin insertion screen!");
        return new ProductLookup(this.vendingMachine);
    }

    @Override
    public State insertCode(String code) {
        System.out.println("Please insert coins first");
        return this;
    }

    @Override
    public State hitCancel() {
        return new IdealState(this.vendingMachine);
    }


    @Override
    public State dispenseSuccess() {
        System.out.println("Please insert coins first");
        return this;
    }
}
