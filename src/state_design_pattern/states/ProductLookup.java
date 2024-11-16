package 
state_design_pattern.states;

import 
state_design_pattern.Product;
import 
state_design_pattern.VendingMachine;

public class ProductLookup implements State{

    VendingMachine vendingMachine;

    public ProductLookup(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
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
        System.out.println("Your inserted code is: " + code);
        Product product = vendingMachine.searchProductWithCode(code);

        if(product == null) {
            System.out.println("Either InsertCode or Hit the Cancel Button");
            return this;
        }
        else {
            if(product.getValue() <= this.vendingMachine.getCurrentCoin()) {
                product.setSoldOut(true);
                return new DispenseProduct(this.vendingMachine, product);
            }
            System.out.println("Insufficient Balance! InsertCode or Hit the Cancel Button");
            return this;
        }
    }

    @Override
    public State hitCancel() {
        return new IdealState(this.vendingMachine);
    }

    @Override
    public State dispenseSuccess() {
        System.out.println("Please insert code");
        return this;
    }
}
