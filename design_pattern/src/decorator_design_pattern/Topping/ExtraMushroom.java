package 
decorator_design_pattern.Topping;

import 
decorator_design_pattern.Pizza.BasePizza;

public class ExtraMushroom extends ToppingPizza {

    BasePizza basePizza;

    public ExtraMushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost() + 100;
    }
}
