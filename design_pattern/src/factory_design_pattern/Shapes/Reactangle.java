package factory_design_pattern.Shapes;

import
factory_design_pattern.Shape;

public class Reactangle implements Shape {

    Integer length, width;

    public Reactangle() {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}
