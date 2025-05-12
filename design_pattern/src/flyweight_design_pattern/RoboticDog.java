package 
flyweight_design_pattern;

public class RoboticDog implements IRobot{

    String type;        // intrinsic
    String body;        // intrinsic

    public RoboticDog(String type, String body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Display this RoboticDog using this (x, y) coordinate " + x + " " + y);
    }
}
