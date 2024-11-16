package 
decorator_design_pattern.Pizza;

public class ChokkyPizza extends BasePizza {

    public ChokkyPizza() {
        super();
        this.pizzaName = "ChokkyPizza";
    }

    @Override
    public int cost() {
        return 100;
    }
}
