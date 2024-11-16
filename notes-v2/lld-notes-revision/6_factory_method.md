## Design Pattern: Factory Method (Creational)

---

> It provides interface(method/constructor..etc) for creating objects in a superclass, but allows the subclasses to alter the type of objects that will be created.

#### <u>Example</u>

---

Currently, your application only support the delivery through `TRUCK` , but due to huge demand now you are getting orders from the USA and UK, for that you need to incorporate the changes by introducing `SHIP` as new logistic. You need to make changes at every place where you created truck objects with some condition right ?

Your codes looks like this. Client doesn't care about which Transport he is getting... he just want to execute `deliver()` method. 

```java
// Client 
public class Logisitc {

    Truck truck;

    // Can create 1-10 trucks for delivery...and when doDelivery() called.... one of truck goes for delivery...
    public Logisitc() {
        truck = new Truck();
    }

    public void doDelivery() {
        System.out.println("Doing delivery...");
        truck.deliver();
    }
}


public class Truck {

    public Truck() {
    }
    public void deliver() {
        System.out.println("Called deliver() method...");
    }
}
```

Let's see how Factory method can help us here...

> The Factory Method pattern suggests that you replace direct object construction calls (using the `new` operator) with calls to a special *factory* method. Don’t worry: the objects are still created via the `new` operator, but it’s being called from within the factory method. Objects returned by a factory method are often referred to as *products.*

Replace direct object construction calls with some speical factory method `createTrasport()`. 

```java
abstract public class Logistic {

    public Logistic() {
    }

    public void doDelivery() {
        // Client code invoking deliver() method.
        Transport transport = createTrasport();
        transport.deliver();
    }

    abstract Transport createTrasport();
}
```

Child classes decide which type of Transport they are creating by override the factory method. 

```java
public class RoadLogistic extends Logistic {
    @Override
    Transport createTrasport() {
        return new Truck();
    }
}


public class SeaLogistic extends Logistic {
    @Override
    Transport createTrasport() {
        return new Ship();
    }
}
```

Should have some common functionality among the product sub-type so we can create interface/abstract class (Transport)  which is returned by the Creator(`Logistic`).

```java
interface Transport {
    void deliver();
}

public class Truck implements Transport{
    @Override
    public void deliver() {
        System.out.println("Delivering through Truck....");
    }
}

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering through Truck....");
    }
}
```

Main Client:

```java
public class FactoryMethodClient {

    public static void run() {
        Logistic logistic = new RoadLogistic();
        logistic.doDelivery();
    }
}
```

What happend is that, we delegated object creation part to subclasses, and use of that object is still in super-classes, but doing that we allowed to incorporate to have more product types in our system, just need to extend the   `Logistic` class and implment the factory method `createTransport()`.  

UML:

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-09-29-17-57-52-image.png)

Example-2: creating cross-platform UI elements without coupling the client code to concrete UI classes.

Just adding UML here(hope I understand):

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-09-29-17-58-37-image.png)
