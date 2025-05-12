package 
decorator_design_pattern;


import 
decorator_design_pattern.Pizza.BasePizza;
import 
decorator_design_pattern.Pizza.Margerita;
import 
decorator_design_pattern.Topping.ExtraCheese;

public class DecoratorDesignClient {

    public static void run() {

        BasePizza margeritaWithCheese = new ExtraCheese(new Margerita());
        System.out.println("Total cost " + margeritaWithCheese.cost()); // 150 + 50 => 200
    }
}
