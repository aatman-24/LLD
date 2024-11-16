# SOLID PRINCIPLE

> The SOLID principles are a set of guidelines that aim to improve the design and maintainability of software systems. Each principle focuses on a specific aspect of software development and helps in achieving code that is modular, extensible, and easy to understand.

---

## Single Responsibility Principle (S):

> The Single Responsibility Principle states that a class should have only one reason to change, meaning it should have only one responsibility. This principle promotes a high level of cohesion and makes classes easier to understand, test, and maintain.
> Example: Consider a class called EmailSender that is responsible for sending emails. It should only handle the logic related to sending emails and not perform any other unrelated tasks like logging or data validation. By adhering to the Single Responsibility Principle, we would separate the logging and data validation concerns into separate classes, such as Logger and Validator, respectively.

---

## Open/Closed Principle (O):

> The Open/Closed Principle states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This means that we should be able to add new functionality without modifying existing code, thus avoiding potential side effects.
>    Example: Suppose we have a class called Shape with a method calculateArea(). Instead of modifying the Shape class every time we need to add a new shape, we can extend the class to create new shape-specific classes like Circle and Rectangle. These subclasses would inherit the calculateArea() method and implement it according to their specific shape requirements, without modifying the existing code.

---

## Liskov Substitution Principle (L):

> The Liskov Substitution Principle states that objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program. In other words, subclasses should be able to substitute their parent classes without introducing errors or unexpected behavior.
>    Example: Consider a class hierarchy where Rectangle is a superclass and Square is a subclass. According to the Liskov Substitution Principle, we should be able to replace a Rectangle object with a Square object without causing any issues. Both classes have common methods like getWidth() and getHeight(), but in the case of Square, these methods should enforce equal width and height, as it is a special case of a rectangle.

---

## Interface Segregation Principle (I):

> The Interface Segregation Principle states that clients should not be forced to depend on interfaces they do not use. It promotes the idea of smaller and more focused interfaces, rather than having a single large interface that caters to multiple scenarios.
>    Example: Suppose we have an interface called Printer with methods like print(), scan(), and fax(). However, not all client classes need to perform all these actions. Instead of having a monolithic Printer interface, we can segregate it into smaller interfaces like Printable, Scannable, and Faxable. This way, client classes can depend only on the interfaces that are relevant to their specific needs.

---

## Dependency Inversion Principle (D):

> The Dependency Inversion Principle states that high-level modules should not depend on low-level modules. Both should depend on abstractions. It encourages the use of interfaces or abstract classes to decouple higher-level components from lower-level implementation details.
>    Example: Consider a scenario where a Customer class directly depends on a concrete database implementation like MySQLDatabase. By adhering to the Dependency Inversion Principle, we would introduce an interface or an abstract class, such as Database, and make both Customer and MySQLDatabase depend on it. This way, the Customer class can use the abstraction without being tightly coupled to Database