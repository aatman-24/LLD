package 
decorator_design_pattern.Topping;

import 
decorator_design_pattern.Pizza.BasePizza;

public class ExtraCheese extends ToppingPizza{

    BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost() + 50;
    }
}
