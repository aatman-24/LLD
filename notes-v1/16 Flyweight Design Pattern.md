Flyweight Design Pattern (Structural Design Pattern)
---

> It is used to minimize memory usage or computational expenses by sharing as much as possible with similar objects. It's particularly useful when dealing with a large number of objects that have some shared state.

### When to use?

    - When you have many objects that consume a lot of memory, and most of the object state can be shared.
    - When you need to reduce the memory footprint by sharing common parts of state among multiple objects.

### Which problem it solves?

> The Flyweight Pattern solves the problem of high memory consumption due to the large number of similar objects. It achieves this by sharing common parts of the object state, thereby reducing the amount of memory required to represent these objects.


### How it solves?

Intrinsic State: This is the state that is shared among multiple objects and is stored in the flyweight object.
Extrinsic State: This is the state that is unique to each object and is passed to the flyweight methods when needed.
The pattern uses a factory to manage the flyweights, ensuring that shared objects are reused and not duplicated. This reduces the overall number of objects and minimizes memory usage.

- From object, remove all the extrinsic data and keep only intrinsic data(this object called flyweight object)
- Extrinsic data can be passed in the parameter to the flyweight class.
- Caching can be used for the flyweight object and used whenever required.

### Standard Template:

Flyweight: Declares an interface through which flyweights can receive and act on extrinsic state.
ConcreteFlyweight: Implements the Flyweight interface and adds storage for intrinsic state.
FlyweightFactory: Creates and manages flyweight objects, ensuring that they are shared properly.
Client: Maintains references to flyweight objects and computes or stores the extrinsic state of flyweights.

    public interface Flyweight {
        void operation(String extrinsicState);
    }

    public class ConcreteFlyweight implements Flyweight {
        private final String intrinsicState;
    
        public ConcreteFlyweight(String intrinsicState) {
            this.intrinsicState = intrinsicState;
        }
    
        @Override
        public void operation(String extrinsicState) {
            System.out.println("Intrinsic State: " + intrinsicState + ", Extrinsic State: " + extrinsicState);
        }
    }

    public class FlyweightFactory {
        private final Map<String, Flyweight> flyweights = new HashMap<>();
    
        public Flyweight getFlyweight(String key) {
            if (!flyweights.containsKey(key)) {
                flyweights.put(key, new ConcreteFlyweight(key));
            }
            return flyweights.get(key);
        }
    
        public int getFlyweightCount() {
            return flyweights.size();
        }
    }

    public class Client {
        public static void main(String[] args) {
            FlyweightFactory factory = new FlyweightFactory();
    
            Flyweight flyweight1 = factory.getFlyweight("A");
            flyweight1.operation("First Call");
    
            Flyweight flyweight2 = factory.getFlyweight("B");
            flyweight2.operation("Second Call");
    
            Flyweight flyweight3 = factory.getFlyweight("A");
            flyweight3.operation("Third Call");
    
            System.out.println("Number of flyweights created: " + factory.getFlyweightCount());
        }
    }

### Example 1: Design Game, specifically design army of robots. 

What is problem ?

    public class Robot {

            int coordinateX;    // 4 bytes
            int coordinateY;    // 4 bytes
            String type;        // 50 Bytes (1 byte * 50 char length)
            String body;        // 2d bitmap, 31 KB             // EST: 31 KB
        
        public Robot(int coordinateX, int coordinateY, String type, String body) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
            this.type = type;
            this.body = body;
        }
    }

    public class FlyweightDesignClient {

        public static void run() {
        
            int x = 0;
            int y = 0;
            
            for(int i = 1; i <= 500000; i++) {
                String humanoidSprites = "BITMAP_OF_IMAGE";
                Robot humaniodRobot = new Robot(x+i, y+i, "HUMANOID", humanoidSprites);  // 5L * 31KB = 15 GB RAM is required. 
            }
            
        }
    }

YOU see we need device that has at least 15 GB RAM to process this army creation. 

How to solve with Flyweight Pattern ?
- Find out intrinsic and extrinsic data, use caching. 


    public interface IRobot {
        void display(int x, int y);
    }

    public class HumanoidRobot implements IRobot{
    
        String type;        // intrinsic
        String body;        // intrinsic
    
        public HumanoidRobot(String type, String body) {
            this.type = type;
            this.body = body;
        }
    
        @Override
        public void display(int x, int y) {
            System.out.println("Display this HumanoidRobot using this (x, y) coordinate " + x + " " + y);
        }
    }

    public class RoboticDog implements IRobot{
    
        String type;        // intrinsic
        String body;        // intrinsic
    
        public RoboticDog(String type, String body) {
            this.type = type;
            this.body = body;
        }
    
        @Override
        public void display(int x, int y) {
            System.out.println("Display this RoboticDog using this (x, y) coordinate " + x + " " + y);
        }
    }

    public class RoboticFactory {
    
        private static Map<String, IRobot> roboticObjectCache = new HashMap<>();
    
        public static IRobot createRobot(String robotType){
    
            if(roboticObjectCache.containsKey(robotType)){
                return roboticObjectCache.get(robotType);
            }
            else {
                if(robotType == "HUMANOID"){
                    String humanoidSprite = new String();
                    IRobot humanoidObject = new HumanoidRobot(robotType, humanoidSprite);
                    roboticObjectCache.put(robotType, humanoidObject);
                    return humanoidObject;
                }
                else if(robotType == "ROBOTICDOG"){
                    String roboticDogSprite = new String();
                    IRobot roboticDogObject = new RoboticDog(robotType, roboticDogSprite);
                    roboticObjectCache.put(robotType, roboticDogObject);
                    return roboticDogObject;
                }
            }
            return null;
        }
    }

    public class FlyweightDesignClient {
    
        public static void run() {
    
            IRobot humanoidRobot1 = RoboticFactory.createRobot("HUMANOID");
            humanoidRobot1.display(1,2);
            
            IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID");
            humanoidRobot2.display(10,30);
    
            IRobot roboDog1 = RoboticFactory.createRobot("ROBOTICDOG");
            roboDog1.display(2,9);
    
            IRobot roboDog2 = RoboticFactory.createRobot("ROBOTICDOG");
            roboDog2.display(11,19);
            
        }
    }

### Example 2: Word Processor

    
    public interface ILetter {
        public void display(int row, int column);
    }

    public class DocumentCharacter implements ILetter{
    
        private char character;         // intrinsic
        private String fontType;        // intrinsic
        private int size;               // intrinsic
    
        DocumentCharacter(char character, String fontType, int size){
            this.character = character;
            this.fontType = fontType;
            this.size = size;
    
        }
    
        //only getter methods
    
        @Override
        public void display(int row, int column) {
    
            //display the character of particular font and size
            //at given location
        }
    }

    public class LetterFactor {
    
        private static Map<Character, ILetter> characterCache = new HashMap<>();
    
        public static ILetter crateLetter(char characterValue){
    
            if(characterCache.containsKey(characterValue)){
                return characterCache.get(characterValue);
            }
            else {
    
                DocumentCharacter characterObj = new DocumentCharacter(characterValue, "Arial", 10);
                characterCache.put(characterValue, characterObj);
                return characterObj;
            }
        }
    }

    public class Main {
    
        public static void main(String args[]){
    
            /*
                this is the data we want to write into the word processor.
    
                Total = 58 characters
                t = 7 times
                h = 3 times
                a = 3 times and so on...
    
             */
    
            ILetter object1 = LetterFactor.crateLetter('t');
            object1.display(0,0);
    
            ILetter object2 = LetterFactor.crateLetter('t');
            object1.display(0,6);
    
        }
    }
