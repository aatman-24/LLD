### Design Pattern: Observer Design Pattern(Behavioural)

---

## Intent

**Observer** is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects(subscribers) about any events that happen to the object(publisher) they’re observing.

---

## Problem

This is classical design pattern, which solve specific use case of pub-sub model, so problem statment looks like... "when change in state of one object, you want to notify set of objects about that". 

But let's see... problem..

Scenario-1:

![](../../assets/2024-11-09-11-48-04-image.png)

Scenario-2:

![](../../assets/2024-11-09-11-48-34-image.png)



## Solution

We need kind of mechanism in which, customer can get notification for interested products only. 



The object that has some interesting state is often called *subject*, but since it’s also going to notify other objects about the changes to its state, we’ll call it *publisher*. All other objects that want to track changes to the publisher’s state are called *subscribers*.



The Observer pattern suggests that you add a subscription mechanism to the publisher class so individual objects can subscribe to or unsubscribe from a stream of events coming from that publisher.

![](../../assets/2024-11-09-11-51-29-image.png)

Now, whenever an important event happens to the publisher, it goes over its subscribers and calls the specific notification method on their objects.



**Real apps might have dozens of different subscriber classes that are interested in tracking events of the same publisher class. You wouldn’t want to couple the publisher to all of those classes. Besides, you might not even know about some of them beforehand if your publisher class is supposed to be used by other people.**



T<mark>hat’s why it’s crucial that all subscribers implement the same interfac</mark>e <mark>and that the publisher communicates with them only via that interface. </mark>This interface should declare the notification method along with a set of parameters that the publisher can use to pass some contextual data along with the notification.



![](../../assets/2024-11-09-11-52-48-image.png)



If your app has several different types of publishers and you want to make your subscribers compatible with all of them, you can go even further and make all publishers follow the same interface. This interface would only need to describe a few subscription methods. The interface would allow subscribers to observe publishers’ states without coupling to their concrete classes.



## Structure

![](../../assets/2024-11-09-11-53-46-image.png)

![](../../assets/2024-11-09-11-53-59-image.png)

## Applicability

Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.

 Use the pattern when some objects in your app must observe others, but only for a limited time or in specific cases.
