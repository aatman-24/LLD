### Design Pattern: State (Behaviour Design Pattern)

---

> **State** is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.



<u>Problem:</u>


In this pattern, when we have finite number of states, and each state, an object change its behaviour based on that. So to implement that part, we add many if-else conditions which define in which state the current object and which state it is going to and additionally logic specific behaviour to that state. 

But when project grow and large number of states we have, that time code becomes messy. 

Imagine that we have a `Document` class. A document can be in one of three states: `Draft`, `Moderation` and `Published`. The `publish` method of the document works a little bit differently in each state:

- In `Draft`, it moves the document to moderation.
- In `Moderation`, it makes the document public, but only if the current user is an administrator.
- In `Published`, it doesn’t do anything at all.



<u>Solution:</u>

This Design Pattern, apply to solve that specific behaviour, when one object can have multiple state and in each state have speicifc behaviour, which object need to incorporate. 

Let's jump to solution...



**The State pattern suggests that you create new classes for all possible states of an object and extract all state-specific behaviors into these classes.**

**Instead of implementing all behaviors on its own, the original object, called *context*, stores a reference to one of the state objects that represents its current state, and delegates all the state-related work to that object.**



![](../../assets/2024-10-27-14-33-27-image.png)

To transition the context into another state, replace the active state object with another object that represents that new state. This is possible only if all state classes follow the same interface and the context itself works with these objects through that interface.



<u>Real-World Analogy:</u>



![](../../assets/2024-10-27-14-34-32-image.png)



<u>Structure:</u>



![](../../assets/2024-10-27-14-35-07-image.png)

![](../../assets/2024-10-27-14-35-18-image.png)

See the #4 point, to change state, we can set it through either `Context` class or StateImpl class also  have access to `context object`, where we can use the `changeState()` method as well, both are fine. 



<u>Pseudocode:</u>



![](../../assets/2024-10-27-14-37-01-image.png)



Notice how, `Player` class have same method of `State` , so that it can deletgate that work to State. 

 
<u>Applicability: </u>



![](../../assets/2024-10-27-14-37-56-image.png)



![](../../assets/2024-10-27-14-38-17-image.png)
