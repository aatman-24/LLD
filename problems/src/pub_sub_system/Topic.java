package pub_sub_system;

import java.util.HashSet;
import java.util.Set;

public class Topic {

    private Set<Subscriber> subscriberSet;
    private String name;

    public Topic(String name) {
        this.name = name;
        subscriberSet = new HashSet<>();
    }

    void subscribeTopic(Subscriber subscriber) {
        subscriberSet.add(subscriber);
    }

    void unsubscribeTopic(Subscriber subscriber) {
        subscriberSet.remove(subscriber);
    }

    void publish(Message message) {
        for(Subscriber subscriber : subscriberSet) {
            subscriber.onMessage(message.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
