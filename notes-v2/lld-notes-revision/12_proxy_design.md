##### Design Pattern: Proxy Design Pattern (Structural design)

---

> The Proxy pattern provides a placeholder for another object to control access to it. (Proxy controls access to real objects(Ex...BookParser)) It is used to provide controlled access to an object by defining a proxy class that represents the original object. Proxy `has-a` relationship with realObject and `is-a` relationship with same interface real object used.

<u>Problem:</u>

Why is need of controlling access to object ? Because, sometime you have a massive object that consumes a vast amount of system resources. You need it from time to time, but not always. I can do lazy initialization of this object, when it's actually needed(some method invokation of that object). Still if there are many clients would need to execute some deferred initialization codes. Unfortunately, this would probably cause a lot of code duplication. Also, if heavy object is coming from the 3rd party library you cannot lazy initialize it, because you are not owning that.  

<u>Solution:</u>

The Proxy pattern suggests that you create a new proxy class with the same interface as an original service object. Then you update your app so that it passes the proxy object to all of the original object’s clients. Upon receiving a request from a client, the proxy creates a real service object and delegates all the work to it.

<u>Usecase:</u>

But what’s the benefit? If you need to execute something either before or after the primary logic of the class, the proxy lets you do this without changing that class. Since the proxy implements the same interface as the original class, it can be passed to any client that expects a real service object.

<u>Strcuture:</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-13-21-19-20-image.png)

1. The **Service Interface** declares the interface of the Service. The proxy must follow this interface to be able to disguise itself as a service object.

2. The **Service** is a class that provides some useful business logic.

3. The **Proxy** class has a reference field that points to a service object. After the proxy finishes its processing (e.g., lazy initialization, logging, access control, caching, etc.), it passes the request to the service object.
   
   Usually, proxies manage the full lifecycle of their service objects.

4. The **Client** should work with both services and proxies via the same interface. This way you can pass a proxy into any code that expects a service object.

<u>Pseudocode:</u>

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-13-21-20-06-image.png)
