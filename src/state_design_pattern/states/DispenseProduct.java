package 
state_design_pattern.states;

import 
state_design_pattern.Product;
import 
state_design_pattern.VendingMachine;

public class DispenseProduct implements State{

    VendingMachine vendingMachine;
    Product product;

    public DispenseProduct(VendingMachine vendingMachine, Product product) {
        this.vendingMachine = vendingMachine;
        this.product = product;
    }

    @Override
    public State insertCoin(int coinValue) {
        System.out.println("Insertion is locked");
        return this;
    }

    @Override
    public State coinInsertionDone() {
        System.out.println("You already clicked on done button!!");
        return this;
    }

    @Override
    public State insertCode(String code) {
        System.out.println("You have already inserted code!!");
        return this;
    }

    @Override
    public State hitCancel() {
        this.product.setSoldOut(false);
        return new IdealState(this.vendingMachine);
    }

    @Override
    public State dispenseSuccess() {
        System.out.println("We are dispensing this product: " + this.product.getCode());
        System.out.println("Your remaining amount: " + (this.vendingMachine.getCurrentCoin() - this.product.getValue()));
        System.out.println("Thank-you!!");
        return new IdealState(this.vendingMachine);
    }
}
