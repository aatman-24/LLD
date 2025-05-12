package 
decorator_design_pattern.Pizza;

public abstract class BasePizza {
    String pizzaName;

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public abstract int cost();
}
