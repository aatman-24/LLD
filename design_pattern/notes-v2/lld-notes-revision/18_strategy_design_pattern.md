Design Pattern: Startegy Design Pattern(Behavioral)
---

---

> It lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.



## Problem

Suppose, You are making an "Navigation" application, in which user wants to go From A => B. So based upon different kind of transportation like through the car, train, bus and also walking you are showing the navigation to the users. Initially, you started with "car" only and now you incoporated all these mechanism in your application, within single class based upon if-else ladder(or switch condition). Problem is that, if you are changing one of mechanism you need to test entire class, or there is high chance that it introduce bug, and each invocation one of block is executing.



## Solution

The Strategy pattern suggests that you take a class that does something specific in a lot of different ways and extract all of these algorithms into separate classes called *strategies*.

The original class, called *context*, must have a field for storing a reference to one of the strategies. The context delegates the work to a linked strategy object instead of executing it on its own.

The context isn’t responsible for selecting an appropriate algorithm for the job. Instead, the client passes the desired strategy to the context. In fact, the context doesn’t know much about strategies. It works with all strategies through the same generic interface, which only exposes a single method for triggering the algorithm encapsulated within the selected strategy.

![](../../assets/2024-11-09-10-56-02-image.png)



## Structure

![](../../assets/2024-11-09-10-56-22-image.png)



## Applicability

 Use the Strategy pattern when you want to use different variants of an algorithm within an object and be able to switch from one algorithm to another during runtime.

 Use the Strategy when you have a lot of similar classes that only differ in the way they execute some behavior.

 Use the pattern when your class has a massive conditional statement that switches between different variants of the same algorithm.
