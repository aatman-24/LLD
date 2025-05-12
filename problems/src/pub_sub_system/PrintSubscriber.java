package pub_sub_system;

public class PrintSubscriber implements Subscriber {

    private String name;

    public PrintSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(String message) {
        System.out.println(name + ": " + message);
    }
}
