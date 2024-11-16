package factory_design_pattern;

public class FactoryDesignClient {

    public static void run() {

        Shape circle = ShapeFactory.getShape(Constants.CIRCLE, null, null, null, 10);
        System.out.println(circle.calculateArea());

        Shape rectangle = ShapeFactory.getShape(Constants.RECTANGLE, 5, 15, null, null);
        System.out.println(rectangle.calculateArea());

        Shape square = ShapeFactory.getShape(Constants.SQUARE, 5, null, null, null);
        System.out.println(square.calculateArea());

        Shape triangle = ShapeFactory.getShape(Constants.TRIANGLE, 10, 10, 10, null);
        System.out.println(triangle.calculateArea());

    }
}
