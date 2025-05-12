>> The Strategy Pattern defines a family of algorithms,
encapsulates each one, and makes them interchangeable.
Strategy lets the algorithm vary independently from
clients that use it.

### Stage-1:
> Duck class have all the methods, display() is diff for all the Children's classes, so they impl by their own. 

Parent class:

    Duck
        quack()
        swim()
        display() -> This is diff for all the child classes. 
 
Child class:
   
    MallardDuck
        display()

    RedheadDuck
        display()

    RubberDuck
        display()
        quack() -> special case, RubberDuck don't do the quack, so we need to remove this feature. 

### Stage-2:
> New requirement comes up, now Duck can fly, so we need to add fly() method for each of ducks. 

Parent class:

    Duck
        quack()
        swim()
        display() 
        fly() -> I will add it here, so don't need to write for each of the child classes, make sense!

Child class:

    MallardDuck
        display()

    RedheadDuck
        display()

    RubberDuck
        display()
        quack() -> special case, RubberDuck don't do the quack, so we need to remove this feature. 

### Stage-3:
> The New requirement wasn't clear, And they told me RubberDuck can't fly. 

- So a first option is, I override fly() method in RubberDuck as I did quack() to remove some feature. 

Parent class:

    Duck
        quack()
        swim()
        display() 
        fly()

Child class:

    MallardDuck
        display()

    RedheadDuck
        display()

    RubberDuck
        display()
        quack() -> RubberDuck don't do the quack, so we need to remove this feature.  
        fly() -> RubberDuck don't do the fly, so we need to remove this feature.

### Stage-4:
> Now, what if there is another duck type, WoodenDuck is coming up which can't quack and fly

Parent class:

    Duck
        quack()
        swim()
        display() 
        fly()

Child class:

    MallardDuck
        display()

    RedheadDuck
        display()

    RubberDuck
        display()
        quack() -> RubberDuck don't do the quack, so we need to remove this feature.  
        fly() -> RubberDuck don't do the fly, so we need to remove this feature.

    WoodenDuck
        display()
        quack() -> RubberDuck don't do the quack, so we need to remove this feature.  
        fly() -> RubberDuck don't do the fly, so we need to remove this feature.

Now, code is duplicated in RubberDuck & WoodenDuck. And one more thing is that, as we add more and more duck type, code maintenance has become a problem.

---
Just using the inheritance, We face a problem likes

    - Code is duplicated across subclasses 
    - Runtime behavior changes are difficult
---

>> An interface defines a set of behaviors that implementing classes must adhere to, ensuring consistency. However, it does not prevent subclasses from changing their implementation of these behaviors. 

### Stage-5:
> Solving the consistency of behavior among the subclasses, by introducing the interface. 

Parent class:

    Duck 
        swim()
        display() 

Interface:
    
    IFlyable
        fly()           # Moved the fly method out fo Duck class, child can have their own impl.     
    
    IQuackable
        quack()          # Moved the quack method out fo Duck class , child can have their own impl.

Child class:

    MallardDuck (IFlyable, IQuackable)
        display()
        fly()       
        quack()

    RedheadDuck(IFlyable, IQuackable)   
        display()
        fly()
        quack()

    RubberDuck(IQuackable)      #Added quack() by intentionally, so don't compare with previous code. 
        quack()
        display()

    WoodenDuck
        display()

Problem: Now, suppose you have to make a little change in fly() method, which is mandatory for each of the class. You need to go and 
make that change for each class. What if there are 50 subclasses who implemented that fly() method? Even we have a bigger problem now? 

> But while having the subclasses implement Flyable and/or Quackable solves part of the
problem (no inappropriately flying rubber ducks), it completely
destroys code reuse for those behaviors, so it just creates a different maintenance nightmare.

Ultimate problem right now:
> We tried inheritance, but the issue is that the behavior of the ducks is keep changing across the subclasses. Some fly, quack and some not... (consistency issue) 
> We tried to resolve it partially by adding the interface. But again, if we need to modify the single behavior ex... fly() method. We need to do it in all subclasses. 

Solution to the above problem:

>> Identify the aspects of your application that vary and separate them from what stays the same. 
>> In other words, if you’ve got some aspect of your code that is changing, say with every new requirement, then you know you’ve got a behavior that needs to be pulled out and separated from all
the stuff that doesn’t change.

### Stage-6:
> Separating what changes from what stays the same. 

- we know fly() and quack() behaviors are changing for subclasses, and the other thing remains. We need to pull out this behavior from the Duck class itself. And create two more separate classes which take the responsibility of these behaviour. 
- So now if any change in behavior, it will happen in these new classes, not in Duck. 
- IFlyable can have many behaviors like IFlyableOnRoadImpl,IFlyableOnWaterImpl, IFlyableOnAirImpl... And we know we need to assign this behavior to duck class. So if we again make it a rigid and tight couple, there is no point
- We want to change behavior dynamically! What it means, Duck behavior can be changed at a later stage, Duck don't need to worry about it, duck only need to know it has capability of flying. 

Parent class:

    Duck 
        swim()
        display() 

Interface:

    IFlyable
        fly()          
    
    IQuackable
        quack() 

New impl classes of IFlyable interface: 

    FlyWithWingsImpl
        fly()
    
    FlyNoWayImpl
        fly()

What is diff between previous method and this methods ?
> Previously, we were either adding impl in parent class(if all subclass behave in same way) or for each subclass have its own impl. In both cases we were relying on an implementation. We
were locked into using that specific implementation and there was no room for changing the behavior. But now, we have own behavior class we can change it or add new behavior class. 

---
>> Program to an interface, not an implementation

    Animal
        makeSound()

    Dog implement Animal
        makeSound()
    
    // This what I mean, Program to an interface, not an implementation
    Animal animal = new Dog();
    animal.makeSound();
---


### Stage-7:
> Let's implement what we learn in the above phase for duck behaviors. 


Parent class:

    Duck 
        swim()
        display() 

Interface:

    IFlyable
        fly()          
    
    IQuackable
        quack() 

New impl classes of IFlyable interface:

    FlyWithWingsImpl
        fly()
    
    FlyNoWayImpl
        fly()
    
    QuackImpl
        quack()

    SqueakImpl
        quack()

    MuteQuack
        quack()

### Stage-8:
> Integrating the Duck Behaviors. Delegate its flying and quacking behaviors, to the child classes instead of worrying about it.
> We define IFlyable & IQuackable in duck behavior, but using constructor injection we give flexibility to caller which behavior they want to use. 

Parent class:

    Duck  
        IFlyable iflyable;
        IQuackable iQuackable;
        Duck(IFlyable iflyable, IQuackable iQuackable)
        swim()
        display() 
        performFly() {
            iflyable.fly()
        }
        performQuack() {
            iQuackable.quack()
        }

Interface:

    IFlyable
        fly()          
    
    IQuackable
        quack() 

New impl classes of IFlyable interface:

    FlyWithWingsImpl
        fly()
    
    FlyNoWayImpl
        fly()
    
    QuackImpl
        quack()

    SqueakImpl
        quack()

    MuteQuack
        quack()


Child class:

    MallardDuck (Duck)
        MallardDuck() {
            super(new FlyWithWingsImpl(), new SqueakImpl())             // see how behavior play roles
        }
        display()

    RedheadDuck(IFlyable, IQuackable)
        RedheadDuck() {
            super(new FlyNoWayImpl(), new QuackImpl())                  // see how behavior play roles
        }
        display()

    RubberDuck(IQuackable)  
        RubberDuck() {
            super(new FlyNoWayImpl(), new SqueakImpl())             // see how behavior play roles
        }
        display()

    WoodenDuck
        WoodenDuck() {
            super(new FlyNoWayImpl(), new MuteQuack())              // see how behavior play roles
        }
        display()

### Stage-9:
> Make it more dynamic by adding getter and setter of IFlyable & IQuackable. After the initialization even we can change it's behavior. 

Parent class:

    Duck  
        IFlyable iflyable;
        IQuackable iQuackable;
        Duck(IFlyable iflyable, IQuackable iQuackable)
        swim()
        display() 
        performFly() {
            iflyable.fly()
        }
        performQuack() {
            iQuackable.quack()
        }
        getIFlyable()
        setIFlyable(IFlyable iflyable)  # same for IQuackable....