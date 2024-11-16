Composite Design Pattern (Structural Design Pattern)
---

### When to Use?
> The Composite Design Pattern is used when you need to treat a group of objects in a similar way as a single object. It is particularly useful in situations where you have a hierarchical structure of objects, such as a tree structure, and you want to perform operations uniformly across both individual objects and composites of objects.

Typical scenarios include: 

    Representing part-whole hierarchies of objects.
    Enabling clients to treat individual objects and compositions of objects uniformly.
    Simplifying client code that deals with complex tree structures.

### Which Problem it Solves?
> The Composite Design Pattern solves the problem of having to deal with individual objects and compositions of objects differently. It allows you to compose objects into tree structures to represent part-whole hierarchies and lets clients treat individual objects and compositions uniformly. 
> This pattern avoids the need for clients to write specific code for handling individual objects and composite objects differently, if-else condition. 


### Standard Template:

Component

    interface Graphic {
        void draw();
    }

Leaf

    class Line implements Graphic {
        public void draw() {
        // Draw the line
        }
    }

Leaf

    class Circle implements Graphic {
        public void draw() {
        // Draw the circle
        }
    }

Composite

    class Picture implements Graphic {
        
        private List<Graphic> children = new ArrayList<>();
    
        public void draw() {
            for (Graphic g : children) {
                g.draw();
            }
        }
    
        public void add(Graphic g) {
            children.add(g);
        }
    
        public void remove(Graphic g) {
            children.remove(g);
        }
    
        public Graphic getChild(int index) {
            return children.get(index);
        }
    }

### How it solves?

    Unified Interface: The pattern provides a unified interface (Graphic in the example) that both the leaf nodes (simple elements like Line and Circle) and composite nodes (Picture) implement. This allows clients to treat all elements uniformly.
    
    Composite Class: The composite class (Picture) implements the same interface as the leaf nodes and maintains a collection of child components. It delegates operations to its children, allowing clients to interact with composite objects as if they were individual objects.
    
    Recursive Composition: The composite objects can contain other composite objects, allowing for recursive composition. This makes it easy to build complex structures from simpler components.
    
    Simplified Client Code: By using the Composite pattern, client code can be simplified. Clients do not need to differentiate between individual objects and composites; they can interact with all objects through the common interface.

### Standard Example: FileSystem

WithoutPattern:


    public class File {
    
        String fileName;
    
        public File(String fileName) {
            this.fileName = fileName;
        }
    
        public void ls() {
            System.out.println("File: " + this.fileName );
        }
    }
    
    public class Directory {
    
        String directoryName;
        List<Object> objectList;
    
        public Directory(String directoryName) {
            this.directoryName = directoryName;
            objectList = new ArrayList<>();
        }
    
        public void add(Object object) {
            objectList.add(object);
        }
    
        public void ls() {
            System.out.println("Directory Name: " + directoryName);
            for(Object obj: objectList) {
    
                if(obj instanceof File) {
                    ((File) obj).ls();
                }
                else if(obj instanceof Directory) {
                    ((Directory) obj).ls();
                }
            }
        }
    }

WithPattern:

    public interface FileSystem {
        public void ls();
    }
    
    public class File implements FileSystem{
    
        String fileName;
    
        public File(String fileName) {
            this.fileName = fileName;
        }
    
        @Override
        public void ls() {
            System.out.println("File: " + this.fileName );
        }
    }
    
    public class Directory implements FileSystem{
    
        String dirName;
        List<FileSystem> fileSystemList;
    
        public Directory(String dirName) {
            this.dirName = dirName;
            this.fileSystemList = new ArrayList<>();
        }
    
        public void add(FileSystem object) {
            fileSystemList.add(object);
        }
    
        @Override
        public void ls() {
            System.out.println("Directory Name: " + dirName);
            for(FileSystem obj: fileSystemList) {
                obj.ls();
            }
        }
    }


### Example 2: ArithmeticExpression

    public enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE;
    }


    public interface ArithmeticExpression { 
        public int evaluate();
    }

    // Leaf Node
    public class Number implements ArithmeticExpression{
        int value;
    
        public Number(int value){
            this.value = value;
        }
    
        public int evaluate(){
            System.out.println("Number value is :" + value);
            return value;
        }
    }


    public class Expression implements ArithmeticExpression {

        ArithmeticExpression leftExpression;
        ArithmeticExpression rightExpression;
        Operation operation;
    
        public Expression(ArithmeticExpression leftPart, ArithmeticExpression rightPart, Operation operation){
            this.leftExpression = leftPart;
            this.rightExpression = rightPart;
            this.operation = operation;
        }
    
        public int evaluate(){
    
            int value = 0;
            switch (operation){
    
                case ADD:
                    value = leftExpression.evaluate() + rightExpression.evaluate();
                    break;
                case SUBTRACT:
                    value = leftExpression.evaluate() - rightExpression.evaluate();
                    break;
                case DIVIDE:
                    value = leftExpression.evaluate() / rightExpression.evaluate();
                    break;
                case MULTIPLY:
                    value = leftExpression.evaluate() * rightExpression.evaluate();
                    break;
            }
    
           System.out.println("Expression value is :" + value);
            return value;
        }
    }


    public class Main {
    
        public static void main(String args[]){
    
            //2*(1+7)
    
           /*
    
                             *
                           /   \
                         2      +
                               / \
                              1   7
    
            */
    
    
            ArithmeticExpression two = new Number(2);
    
            ArithmeticExpression one = new Number(1);
            ArithmeticExpression seven = new Number(7);
    
            ArithmeticExpression addExpression = new Expression(one,seven, Operation.ADD);
    
            ArithmeticExpression parentExpression = new Expression(two,addExpression, Operation.MULTIPLY);
    
            System.out.println(parentExpression.evaluate());

        }
    }
