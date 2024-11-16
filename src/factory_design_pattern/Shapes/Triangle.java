package factory_design_pattern.Shapes;

import
factory_design_pattern.Shape;

public class Triangle implements Shape {

    Integer side1, side2, side3;

    public Triangle(Integer side1, Integer side2, Integer side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double calculateArea() {
        return side1 * side2 * side3; // Here I know this is wrong equation, but I'm working on understanding design pattern, so ignore it
    }
}
