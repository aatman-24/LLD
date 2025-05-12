### Design Pattern: Prototype (Creational)

---

> **Prototype** is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.

<u>What problem it solves ?</u>

Suppose, I have one `obj`, and I want to make copy of it. Simple way of doing this is, to create a new object of that class and assign all of its fields value to this new object. What if you don't know which concreter class this object is referring, because it is possible object have type as interface. And also, how will you copy the private fields of that class(not possible outside of that class). 

<u>Solution</u>

So here, prototype pattern comes for rescue. Basically, we create one interface `prototype` and its contain one abstract method `clone()`. Now every class which is clonable needs to implement this method and return new object of that class.  

<u>How it solve above two problems ? </u>

Now you are implementing method withing class you can access the private fields, and you don't need to know the actual concrete class as well because you are just calling method clone() on object and object points to one class, which clone() method will be triggered(doesn't matter weather it is pointing to interface or not).

###### #### Structure

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-03-21-13-01-image.png)

Inside, `ConcretePrototype` class you can see Constructor that is called Prototype constructor, which is used to make a object from the prototype of same class (check in example). 

###### What is Prototype registry ?

It is kind of storage, which store pre-built prototype which are frequently used(or will be used), so that can be use to create a new clone. It can be array, map... and based on some search criteria (name, tag..etc) will access specific prototype which required. 

**Examples:**

```java
// Prototype interface
public interface Shape {
    Shape clone();
}


//Concrete Shape class
public class Rectangle implements Shape {

    // private fields
    private int height;
    private int width;

    Rectangle(){}

    Rectangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    // Prototype Constructor
    Rectangle(Rectangle source) {
        this();
        this.height = source.height;
        this.width = source.width;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}


public class ShapeRegistry {

    HashMap<String, Shape> shapes;

    ShapeRegistry() {
        shapes = new HashMap<>(2);
        shapes.put("rectangle", new Rectangle(10, 10));
    }

    Shape getShape(String shapeType) {
        for (String s : shapes.keySet()) {
            if (s.equals(shapeType)) {
                return shapes.get(s).clone();       // here, we are returning a clone object of that shape.
            }
        }
        return null;
    }

    void registerShape(String shapeType, Shape shape) {
        // we can impl same way
    }
}

// Client
public class PrototypeDesignClient {

    public static void run() {
        Shape react1 = new Rectangle(5, 5);
        Shape react2 = react1.clone();
    }
}
```

<u>When to use ?</u>

- When code should not depend on the concrete classes of objects, here in example react2 type is Shape(interface) doesn't depend on Rectangle class property or methods. 

- Use when, you want to reduce the number of subclasses that only differ in the way they initalize their respective objects. Suppose in car(name, model, type, color) all those are configuration needs to be set and to avoid duplicate code...we are making subclass which have fixed model and name... BMWCar("bmw", "a3", type, color)..... and same for other brand... but doing that ultimately increase the subclasses... which is hard to manage... that's where prototype help with registery(where all car model are pre-built stored there, which can easily clone for further use..)
