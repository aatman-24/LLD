## Design Pattern: Facade Design Pattern (Structural Design Pattern)

---

> It provides a simplified interface to a library, a framework, or any other complex set of classes.

<u>Problem</u>

Suppose, In you application, you want to integrate one 3rd party application. So your code is directly using 3rd party application classes, which is tight coupling. Any change in those class, require change in you app code as well. Another problem is that, client is responsible to keep track of dependency of those classes while creating objects, and how to make calls to method in right order and etc...in your code. And last problem is that, what if in future you want to use its alternative... you need to make lots of code change in your code to make it compatiable with new lib. All of these problem can be solved, if we created an facade layer in between. 

<u>Solution</u>

A facade is a class that provides a simple interface to a complex subsystem which contains lots of moving parts. A facade might provide limited functionality in comparison to working with the subsystem directly. However, it includes only those features that clients really care about.

Having a facade is handy when you need to integrate your app with a sophisticated library that has dozens of features, but you just need a tiny bit of its functionality.

<u>Real-world Analogy</u>

When you call a shop to place a phone order, an operator is your facade to all services and departments of the shop. The operator provides you with a simple voice interface to the ordering system, payment gateways, and various delivery services.

<u>Structure</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-13-47-23-image.png)

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-13-47-34-image.png)

<u>Pseudocode</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-20-13-47-56-image.png)

Instead of making your code work with dozens of the framework classes directly, you create a facade class which encapsulates that functionality and hides it from the rest of the code. This structure also helps you to minimize the effort of upgrading to future versions of the framework or replacing it with another one. The only thing you’d need to change in your app would be the implementation of the facade’s methods.

<u>When to Use ? </u>

Use the Facade pattern when you need to have a limited but straightforward interface to a complex subsystem.

 Use the Facade when you want to structure a subsystem into layers.

<u>How to implement ?</u>

1. Check whether it’s possible to provide a simpler interface than what an existing subsystem already provides. You’re on the right track if this interface makes the client code independent from many of the subsystem’s classes.

2. Declare and implement this interface in a new facade class. The facade should redirect the calls from the client code to appropriate objects of the subsystem. The facade should be responsible for initializing the subsystem and managing its further life cycle unless the client code already does this.

3. To get the full benefit from the pattern, make all the client code communicate with the subsystem only via the facade. Now the client code is protected from any changes in the subsystem code. For example, when a subsystem gets upgraded to a new version, you will only need to modify the code in the facade.

4. If the facade becomes [too big](https://refactoring.guru/smells/large-class), consider extracting part of its behavior to a new, refined facade class.
