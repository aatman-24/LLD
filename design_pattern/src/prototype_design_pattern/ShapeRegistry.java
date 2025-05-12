package 
prototype_design_pattern;

import java.util.HashMap;

public class ShapeRegistry {

    HashMap<String, Shape> shapes;

    ShapeRegistry() {
        shapes = new HashMap<>(2);
        shapes.put("rectangle", new Rectangle(10, 10));
    }

    Shape getShape(String shapeType) {
        for (String s : shapes.keySet()) {
            if (s.equals(shapeType)) {
                return shapes.get(s).clone();       // here, we are returning a clone object of that shape.
            }
        }
        return null;
    }

    void registerShape(String shapeType, Shape shape) {

    }
}
