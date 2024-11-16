Bridge Design Pattern (structural pattern)
---
>> It decouples an abstraction from its implementation, allowing the two to vary independently.

> NOTE: It is similar to Strategy Design Pattern in impl wise, but it is differed in intent. 

---

### When to use?

- When you want to switch implementations at runtime
- When you want to extend both the abstraction and the implementation independently.
- When you have a complex class hierarchy, and you want to reduce the complexity by separating the abstraction from its implementation.
- When different objects need to share an implementation

### Which problem it solves?

> The Bridge Pattern solves the problem of having tightly coupled abstraction and implementation in a way that makes it hard to extend both abstraction and implementation independently. 
It prevents a combinatorial explosion of classes when both abstraction and implementation have multiple dimensions that need to be extended.

### Standard Template:

Implementor

    public interface Implementor {
        void operationImpl();
    }

ConcreteImplementor

    public class ConcreteImplementorA implements Implementor {
        @Override
        public void operationImpl() {
            // Implementation
        }
    }

    public class ConcreteImplementorB implements Implementor {
        @Override
        public void operationImpl() {
        // Implementation
        }
    }

Abstraction

    public abstract class Abstraction {
        
        protected Implementor implementor;
    
        protected Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }
    
        public abstract void operation();
    }

RefinedAbstraction

    public class RefinedAbstraction extends Abstraction {
        
        public RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }
    
        @Override
        public void operation() {
            implementor.operationImpl();
        }
    }

Client

    public class Client {
        public static void main(String[] args) {
            Implementor implementorA = new ConcreteImplementorA();
            Abstraction abstractionA = new RefinedAbstraction(implementorA);
            abstractionA.operation();
    
            Implementor implementorB = new ConcreteImplementorB();
            Abstraction abstractionB = new RefinedAbstraction(implementorB);
            abstractionB.operation();
        }
    }

---

### How it solves?

> The Bridge Pattern solves the problem by introducing a  bridge between the abstraction and its implementation.

The abstraction and implementation can be extended independently without affecting each other.
The implementation can be changed at runtime without modifying the abstraction.
The pattern reduces the complexity of having a large number of classes by decoupling the abstraction from its implementation.

---

### Example: RemoteControl Device. 

I configured the remote button currently with TV Device, and now I want to change it to Radio with minimal changes in client code. 

Implementor

    public interface Device {
        public void turnOn();
        public void increaseVolume();
        public void decreaseVolume();
    }
   
ConcreteImpl
 
    public class TvDevice implements Device{
    
        @Override
        public void turnOn() {
            System.out.println("Tv is turned on!");
        }
    
        @Override
        public void increaseVolume() {
            System.out.println("Volume is up for tv");
        }
    
        @Override
        public void decreaseVolume() {
            System.out.println("Volume is down for tv");
        }
    }
    
    public class RadioDevice implements Device{
    
        @Override
        public void turnOn() {
            System.out.println("Radio is turned on!");
        }
    
        @Override
        public void increaseVolume() {
            System.out.println("Volume is up for radio");
        }
    
        @Override
        public void decreaseVolume() {
            System.out.println("Volume is down for radio");
        }
    }

Abstraction

    abstract class RemoteControl {
    
        protected Device device;            // has a impl reference. 
    
        public RemoteControl(Device device) {
            this.device = device;
        }
    
        abstract public void turnOn();
        abstract public void increaseVolume();
        abstract public void decreaseVolume();
    }

RefinedAbstraction

    public class ConfigurableRemoteControl extends RemoteControl{
    
        // Allowed to have any configured device for this remote.
        public ConfigurableRemoteControl(Device device) {
            super(device);
        }
    
        @Override
        public void turnOn() {
            this.device.turnOn();
        }
    
        @Override
        public void increaseVolume() {
            this.device.increaseVolume();
        }
    
        @Override
        public void decreaseVolume() {
            this.device.decreaseVolume();
        }
    }

Client

    public class BridgeDesignClient {

        public static void run() {
    
            ConfigurableRemoteControl tvRemote = new ConfigurableRemoteControl(new TvDevice());
            tvRemote.turnOn();
            tvRemote.increaseVolume();
    
            ConfigurableRemoteControl radioRemote = new ConfigurableRemoteControl(new RadioDevice());       // minimal change requied to convert in radio remote. 
            radioRemote.turnOn();
            radioRemote.increaseVolume();
    
        }
    }
