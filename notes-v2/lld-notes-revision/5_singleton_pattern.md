#### Design Pattern: Singleton(Creational)

---



<u>Multiple ways to implement singleton class:</u>



###### Eager initialization

```java
class Singleton {

    private static final Singleton singleton = new Singleton();

    private Singleton() {};

    public static Singleton getInstance() {
        return singleton;
    }
}

```



###### Lazy Initialization

```java
class Singleton {

    private static Singleton singleton;

    private Singleton() {};

    public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
```



###### Thread-Safe Singleton

```java
class Singleton {

    private static Singleton singleton;

    private Singleton() {};

    synchronized public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}

```



###### Double-Checked Locking

```java
class Singleton {

    private static Singleton singleton;

    private Singleton() {};

    public static Singleton getInstance() {
        // First check (not Synchronized)
        if(singleton == null) {
           // Synchronized on the class object.  
            synchronized (Singleton.class) {
                // Second check (not Synchronized)
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

```



###### Bill Pugh Singleton

```java
class Singleton {
    
    private Singleton() {};
    
    private static class BillPughSingleton {
        // Inside private static class, we eager initialize the instance.  
        private static final BillPughSingleton SINGLETON = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return BillPughSingleton.SINGLETON;
    }
}

```



###### Enum Singleton



```java
public enum Singleton {
    SINGLETON;
}
```



Read when to use and where to use:

https://blog.algomaster.io/p/singleton-design-pattern


