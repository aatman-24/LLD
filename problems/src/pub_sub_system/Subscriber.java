package pub_sub_system;

public interface Subscriber {
    void onMessage(String message);
}
