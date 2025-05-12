#### Design Pattern: Decorator[Wrapper] (Structural Design Pattern)

---

> **Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside `special wrapper` objects that contain the behaviors.

#### 

> "Decorator class has both is-a and has-a relationship with base class. So itself it is a base-class so others can decorate it. While, "has-a" relation gives decorator class to access to its property."



#### <u>Problem:</u>

Imagine that you’re working on a notification library which lets other programs notify their users about important events. It provide the functionality of sending notification through the `Email`. 

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-22-05-image.png)

At some point, you realize that users of the library expect more than just email notifications. Many of them would like to receive an SMS about critical issues. Others would like to be notified on Facebook and, of course, the corporate users would love to get Slack notifications. Solution is to create specific subclass for each behavious. 

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-22-44-image.png)

But then someone reasonably asked you, “<u>Why can’t you use several notification types at once? If your house is on fire, you’d probably want to be informed through every channel.”</u> 

You can think of doing this for first time, 

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-23-54-image.png)

However, it quickly became apparent that this approach would bloat the code immensely, not only the library code but the client code as well. This problem can be solved by Decorator easily..

###### <u>What is root cause of above problem ?</u>

Fundamental problem is, Extending a class. Yes, Inheritance is red signal.  However, inheritance has several serious caveats that you need to be aware of.

- Inheritance is static. You <mark>can’t alter the behavior</mark> of an existing object at runtime. You can <mark>only replace the whole object</mark> with another one that’s created from a different subclass.
- Subclasses can have just one parent class. 

**One of the ways to overcome these caveats is by using *Aggregation* or *Composition*  instead of *Inheritance*.** Both of the alternatives work almost the same way: one object *has a* reference to another and delegates it some work, whereas with inheritance, the object itself *is* able to do that work, inheriting the behavior from its superclass.

#### <u>Solution:</u>

“Wrapper” is the alternative nickname for the Decorator pattern that clearly expresses the main idea of the pattern. A *wrapper* is an object that can be linked with some *target* object. 

The wrapper contains the same set of methods as the target and delegates to it all requests it receives. **However, the wrapper may alter the result by doing something either before or after it passes the request to the target.**

<u>When does a simple wrapper become the real decorator? </u>

As I mentioned, the wrapper implements the same interface as the wrapped object. That’s why from the client’s perspective these objects are identical.

 Make the wrapper’s reference field accept any object that follows that interface. This will let you cover an object in multiple wrappers, adding the combined behavior of all the wrappers to it.

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-31-34-image.png)

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-31-50-image.png)

The last decorator in the stack would be the object that the client actually works with. Since all decorators implement the same interface as the base notifier, the rest of the client code won’t care whether it works with the “pure” notifier object or the decorated one.

#### <u>Structure</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-32-33-image.png)

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-32-47-image.png)

#### <u>Pseudocode</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-21-22-33-14-image.png)

- Just before the data is **written to disk**, the decorators encrypt and compress it. The original class writes the encrypted and protected data to the file without knowing about the change.

- Right after the data is **read from disk**, it goes through the same decorators, which decompress and decode it.

#### <u>Applicability</u>

Use the Decorator pattern when you need to be able to assign extra behaviors to objects at runtime without breaking the code that uses these objects.

Use the pattern when it’s awkward or not possible to extend an object’s behavior using inheritance: :Many programming languages have the `final` keyword that can be used to prevent further extension of a class. For a final class, the only way to reuse the existing behavior would be to wrap the class with your own wrapper, using the Decorator pattern.

## How to Implement

1. Make sure your business domain can be represented as a primary component with multiple optional layers over it.

2. Figure out what methods are common to both the primary component and the optional layers. Create a component interface and declare those methods there.

3. Create a concrete component class and define the base behavior in it.

4. Create a base decorator class. It should have a field for storing a reference to a wrapped object. The field should be declared with the component interface type to allow linking to concrete components as well as decorators. The base decorator must delegate all work to the wrapped object.

5. Make sure all classes implement the component interface.

6. Create concrete decorators by extending them from the base decorator. A concrete decorator must execute its behavior before or after the call to the parent method (which always delegates to the wrapped object).

7. The client code must be responsible for creating decorators and composing them in the way the client needs.

**Classical Example:**

Pizza & Toppings
