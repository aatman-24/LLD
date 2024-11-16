### Head first review of Decorator Design Pattern 

Use-cases:

- Decorate the class at runtime using a form of object decomposition. 
- It gives objects new responsibilities without making any code changes to underlying classes. 

---

### Example: Starbucks coffee

    abstract public class Beverage {

        String description;
    
        public Beverage(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return description;
        }
        abstract public int cost();
    }


    public class HouseBlend extends Beverage{

        public HouseBlend() {
            description = "HouseBlend";
        }
    
        @Override
        public int cost() {
            return 120;
        }
    }

    
    public class DarkRoast extends Beverage {
    
        public DarkRoast() {
            description = "DarkRoast";
        }
    
        @Override
        public int cost() {
            return 120;
        }
    
    }

    public class Espresso extends Beverage{
    
        public Espresso() {
            description = "Espresso";
        }
    
        @Override
        public int cost() {
            return 120;
        }
    }

Now What if there are more condiments like steamed milk, soy, and mocha. which can be added into any coffee? Is it mean I need all kinds of combination classes as well?
For ex: HouseBlendWithStreamedMilkAndMocha, HouseBlendWithStreamedMilk, HouseBlendWithMocha, EspressoWithStreamedMilkAndMocha.... Ohh fuck!

Now What if the underlying condiment like steamed milk price surges to 2% in the market, And a company has decided to compensate from a customer, Do I need to go to each of this 
classes and change the cost() method? It's a huge maintenance problem. 

Okay... What if we store these condiments in Beverage class and also cost is calculated there... subclass will only add their cost on top of that ? 

    Beverage
        description
        milk
        soy
        mocha
        whip

        getDescription() -> Override by subclass
        cost() -> calculate the condiments 

        hasMilk() -> These all are the getter and setters. 
        setMilk()
        hasSoy()
        setSoy()
        hasMocha()
        setMocha()
        hasWhip()
        setWhip()
    
        // impl of cost() in parent class and sub-class
        // Parent class
        public int cost() {
            int total = 0;
            if(this.hasMilk()) {
                total += 20; // milk cost is 20
            }
            if(this.hasSoy()) {
                total += 30;
            }
            if(this.hasMocha()) {
                total += 50;
            }
            if(this.hasWhip()) {
                total += 80;
            }
            return total;
        }

        // child class
        @Override
        public int cost() {
            return this.cost() + 120;
        }

Well, it looks good. You see any problem ? Nope, With the current requirement, it is good. 

But What future req can make us to modification in our code? Describe the scenarios. 
- Price changes for condiments will force us to alter existing code.
- New condiments will force us to add new methods and alter the cost method in the superclass
- We may have new beverages. For some of these beverages (iced tea?), the condiments may
  not be appropriate, yet the Tea subclass will still inherit methods like hasWhip().**** (good one) (I can make it to false.... maybe)
- What if a customer wants a double mocha? **** (good one) How you consider that in cost? (I can make those boolean into int, then do calculation, maybe...)
    
### OPEN-CLOSED PRINCIPLE
>>Classes should be open for extension, but closed for modification.

### Decorator Pattern

- Basically, It attaches additional responsibility to an object dynamically. You don't need to create the subclass for extending functionality. 

Let's use the Decorator pattern to solve this Beverage problem, Here is the Design

    Beverage
      description
      ---
      getDescription()
      cost() -> need to implement by subclass
      
    HouseBlend
      cost() 

    DarkRoast
      cost()

    // Decorator
    CondimentDecorator extend Beverage
        Beverage beverage (Has-A relationship)
        getDescription()

    Milk
      cost() -> basically, you call beverage.cost()(parent method call) + 10; // 10 is milk cost
      getDescription()

    Mocha
      cost()
      getDescription()


### Inheritance versus Composition:

- yes, we are using Inheritance for a matching type of the component for Decorator, so decorator can take place of the component(Beverage). 
- but how we get the behavior of the component?
  - Well, when we are making that (HAS-A) relationship (Composition) with the components, we are adding comp behavior into the decorator. 
  - After that decorator can add any other functionality. 
  - It means that type matching is coming through Inheritance (IS-A) but behavior coming through Composition (HAS-A).


### Imp Notes:
-> Until and unless, you are relying on the abstract/interface in decorator, it will work, but if you are using concrete imp in decorator it will start breaking the code.-


### RealWord Decorator: Java I/0

FileInputStream (Component)
BufferedInputStream(Decorator)
ZipInputStream(Decorator)

### Bad Side:

You see, I can sometimes add a lot of small classes to a design,
and this occasionally results in a design that’s less than straightforward for others to understand.



