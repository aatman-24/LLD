package 
prototype_design_pattern;

//Concrete Shape class
public class Rectangle implements Shape {

    // private fields
    private int height;
    private int width;

    Rectangle(){}

    Rectangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    // Prototype Constructor
    Rectangle(Rectangle source) {
        this();
        this.height = source.height;
        this.width = source.width;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}
