package factory_design_pattern.Shapes;

import
factory_design_pattern.Shape;

public class Circle implements Shape {

    Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 3.14 * radius * radius;
    }
}
