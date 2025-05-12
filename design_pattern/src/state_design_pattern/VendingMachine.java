package 
state_design_pattern;

import 
state_design_pattern.states.IdealState;
import 
state_design_pattern.states.State;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Product> productList;
    private State state;
    private int currentCoin;

    public VendingMachine() {
        this.productList = new ArrayList<>();
        this.currentCoin = 0;
        addFewProducts(productList);
        state = new IdealState(this);
    }


    public VendingMachine(List<Product> productList) {
        this.productList = productList;
        this.currentCoin = 0;
        addFewProducts(productList);
        state = new IdealState(this);
    }

    private void addFewProducts(List<Product> productList) {
        productList.add(new Product(10, "PEPSI"));
        productList.add(new Product(5, "MAAZA"));
        productList.add(new Product(25, "THUMSHUP"));
        productList.add(new Product(30, "COCK"));
    }

    public void insertCoin(int coinValue) {
        // moved to HasCoin State
        state = state.insertCoin(coinValue);
    }

    public void coinInsertionDone() {
        // moved to ProductLookup State
        state = state.coinInsertionDone();
    }

    public void enterCode(String code) {
        // moved to the same state or DispenseProduct State
        state = state.insertCode(code);
    }

    public void cancel() {
        refund();
        state = state.hitCancel();
    }

    public void dispense() {
        state = state.dispenseSuccess();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCurrentCoin() {
        return currentCoin;
    }

    public void setCurrentCoin(int currentCoin) {
        this.currentCoin = currentCoin;
    }

    public Product searchProductWithCode(String code) {
        for(Product p: productList) {
            if(p.getCode().equals(code)) {
                if(!p.isSoldOut()) {
                    return p;
                }
                else {
                    System.out.println("Product is out of stock!!");
                    return null;
                }
            }
        }

        System.out.println("You have entered wrong code!!");
        return null;
    }

    private void refund() {
        System.out.println("Your refund money is: " + this.getCurrentCoin());
    }

}
