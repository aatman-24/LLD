package factory_design_pattern.Shapes;

import
factory_design_pattern.Shape;

public class Square implements Shape {

    Integer side;

    public Square(Integer side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}
