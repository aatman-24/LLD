package 
flyweight_design_pattern;

public class HumanoidRobot implements IRobot{

    String type;        // intrinsic
    String body;        // intrinsic

    public HumanoidRobot(String type, String body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Display this HumanoidRobot using this (x, y) coordinate " + x + " " + y);
    }
}
