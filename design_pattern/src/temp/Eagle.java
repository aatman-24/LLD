package 
temp;

public class Eagle implements Bird{

    private String name;

    @Override
    public void canFly(String val) {
        System.out.println("Flying....." + name);
    }
}
