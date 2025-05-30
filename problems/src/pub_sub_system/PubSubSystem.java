package pub_sub_system;

public class PubSubSystem {

    public static void main(String[] args) {

        // Create topics
        Topic topic1 = new Topic("Topic1");
        Topic topic2 = new Topic("Topic2");

        // Create publishers
        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();

        // Create subscribers
        Subscriber subscriber1 = new PrintSubscriber("Subscriber1");
        Subscriber subscriber2 = new PrintSubscriber("Subscriber2");
        Subscriber subscriber3 = new PrintSubscriber("Subscriber3");

        publisher1.registerTopic(topic1);
        publisher2.registerTopic(topic2);

        // Subscribe to topics
        topic1.subscribeTopic(subscriber1);
        topic1.subscribeTopic(subscriber2);
        topic2.subscribeTopic(subscriber2);
        topic2.subscribeTopic(subscriber3);

        // Publish messages
        publisher1.publish(topic1, new Message("Message1 for Topic1"));
        publisher1.publish(topic1, new Message("Message2 for Topic1"));
        publisher2.publish(topic2, new Message("Message1 for Topic2"));


        // Unsubscribe from a topic
        topic1.unsubscribeTopic(subscriber2);

        // Publish more messages
        publisher1.publish(topic1, new Message("Message3 for Topic1"));
        publisher2.publish(topic2, new Message("Message2 for Topic2"));
    }
}
