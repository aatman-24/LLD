package 
decorator_design_pattern.Pizza;

public class Margerita extends BasePizza {

    public Margerita() {
        super();
        this.pizzaName = "Margerita";
    }

    @Override
    public int cost() {
        return 150;
    }
}
