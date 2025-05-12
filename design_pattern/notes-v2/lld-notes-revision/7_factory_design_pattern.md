## Design Pattern: Factory Design Pattern(Creational)

---

> The **Factory pattern** (also known as the **Simple Factory**) defines a separate method, often in a separate class, that returns an instance of a certain type. The responsibility of object creation is centralized in one place.   

This pattern is different than `Factory method` pattern, here we centralize the object creation part in one of method, and based upon type of subclass type we return that object. 

We can use it when one superclass have many subclasses, we can create one factory which job is to create subclass based on given type. 

Standard Example:

---

Imagine a vehicle rental system where customers can rent different types of vehicles such as cars, bikes, and trucks. The type of vehicle a customer needs can vary based on their requirements. Using the Factory Design Pattern, we can centralize the creation logic of different vehicle types, making the system easier to manage and extend.

Sample Code:

---

```java
// Product Interface
interface Product {
    void use();
}

// Concrete Product 1
class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("Using Product A");
    }
}

// Concrete Product 2
class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("Using Product B");
    }
}

// Factory Class
class ProductFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else if (type.equals("B")) {
            return new ConcreteProductB();
        }
        throw new IllegalArgumentException("Unknown product type");
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Product productA = ProductFactory.createProduct("A");
        productA.use(); // Output: Using Product A

        Product productB = ProductFactory.createProduct("B");
        productB.use(); // Output: Using Product B
    }
}
```
