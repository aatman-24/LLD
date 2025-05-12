package pub_sub_system;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Topic> topics;

    public Publisher() {
        this.topics = new ArrayList<>(10);
    }

    public void registerTopic(Topic topic) {
        topics.add(topic);
    }

    public void publish(Topic topic, Message message) {
        if(!topics.contains(topic)) {
            System.out.println("This publisher cannot publish to this topic: " + topic.getName());
            return;
        }
        topic.publish(message);
    }

    public void publish(List<Topic> topicsList, Message message) {
        for(Topic topic: topicsList) {
            if(!this.topics.contains(topic)) {
                System.out.println("This publisher cannot publish to this topic: " + topic.getName());
                return;
            }
            topic.publish(message);
        }
    }
}
