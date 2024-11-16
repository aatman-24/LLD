package factory_design_pattern;

import
factory_design_pattern.Shapes.Circle;
import
factory_design_pattern.Shapes.Reactangle;
import
factory_design_pattern.Shapes.Square;
import
factory_design_pattern.Shapes.Triangle;

public class ShapeFactory {

    public static Shape getShape(String shapeName, Integer side1, Integer side2, Integer side3, Integer radius) {
        return switch (shapeName) {
            case Constants.CIRCLE -> new Circle(radius);
            case Constants.RECTANGLE -> new Reactangle();
            case Constants.SQUARE -> new Square(side1);
            case Constants.TRIANGLE -> new Triangle(side1, side2, side3);
            default -> null;
        };
    }
}
