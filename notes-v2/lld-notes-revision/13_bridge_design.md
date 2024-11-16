#### Design Pattern: Bridge Design Pattern(Structural)

----

> **Bridge** is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.



##### Problem

---

Suppose we have `Shape` class, which have two subclasses based on shape dimension: `Circle` & `Reactangle`. Now If I want to add the color(Red & Blue) for each of Shape, the easy way comes into mind is to inherit both subclasses by adding new dimension. Four subclasses we have now are: `CircleRed` `CircleBlue` `ReactangleRed` `ReactangleBlue`. 

But now what if want to add new shape `Triangle` and we need diff color of it as well. Which means 3 subclasses based on shape(dimesion) && other 6 subclasses based on color(dimension).



> What is actually happeing for both dimesions Color & Shape, doesn't have any interdependent relation but still we are growing the class exponentially. Each time we try to extend class by some attribute we have to change it in entire hierarachy.





##### Solution

---

Bridge pattern can be used to solve this problem. Problem is that,  we’re trying to extend the shape classes in two independent dimensions: by shape and by color, through inheritance. 

> Bridge pattern attempts to solve this problem by switching from inheritance to the object composition.

What it means is that, you can extract one of dimension as separate hierarchy, and the reference of object of new hierarchy should be linked to main object using `HAS-A` relation(IOC). 

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-23-42-image.png)

We can extract color dimension into other hierarchy. Create `Color` as interface, which have two imple classes `RedColor` and `BlueColor`. And assign referece of Color in `Shape` base-class. Whenever the some task comes related to color, we should delegate that responsibility to the Color classes. 



#### Abstraction and Implementation



What `Gang of Four` Suggested is for abs & impl, 



> > > *Abstraction* (also called *interface*) is a high-level control layer for some entity. This layer isn’t supposed to do any real work on its own. It should delegate the work to the *implementation* layer (also called *platform*).



Note that we’re not talking about *interfaces* or *abstract classes* from your programming language. These aren’t the same things.



When talking about real applications, the abstraction can be represented by a graphical user interface (GUI), and the implementation could be the underlying operating system code (API) which the GUI layer calls in response to user interactions.



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-27-31-image.png)



---



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-28-32-image.png)



Abstraction layers control the apperance of application, delegating the actual work to linked impl objects. Different implementations are interchangeable as long as they follow a common interface, enabling the same GUI to work under Windows and Linux.



## Structure

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-30-54-image.png)

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-31-05-image.png)



## Pseudocode



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-15-10-31-47-image.png)



### When to use?

- When you want to extend both the abstraction and the implementation independently.
- When you have a complex class hierarchy, and you want to reduce the complexity by separating the abstraction from its implementation.
- When you want to switch implementations at runtime (Strategy design pattern, but here intent is different )


