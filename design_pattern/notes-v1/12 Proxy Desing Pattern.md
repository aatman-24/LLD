Proxy Design Pattern(Behavioural Design Pattern)
---

> The Proxy pattern provides a placeholder for another object to control access to it. (Proxy controls access to real objects(Ex...BookParser))
> It is used to provide controlled access to an object by defining a proxy class that represents the original object.

>> Proxy has-a relationship with realObject and is-a relationship with same interface real object used.


### When to use?

Three Types of Use-cases:

    Remote: When you want to access objects running in a different address space, such as accessing a remote server, that time remote proxy helps.
    
    Virutal: There are objects which creation required more utilization of memory and space. So we control initialization of such object using this proxy.
                    — Lazy Initialization: When you want to delay the creation and initialization of an object until it is actually needed.
                    — Control Access to an Object: When you need to control access to an object, especially when the object is resource-intensive to create.
    
    Protection: When you want to add a layer of security to access the real object.
    
    Logging and Caching: When you want to log requests or cache results without modifying the actual object.

    Preprocessing and postprocessing, Publish Event: Before/After making call to real object we can do these operation.


### Which problem it solves?

> The Proxy pattern solves the problem of controlling access to an object by creating a placeholder object that performs additional actions before delegating to the actual object. This can include lazy initialization, access control, logging, caching, etc., without changing the original object's code.

### Standard Template:

Subject interface

    public interface Subject {
        void request();
    }

RealSubject Impl class

    public class RealSubject implements Subject {
        @Override
        public void request() {
            // Original functionality
        }
    }

Proxy class

    public class Proxy implements Subject {
        private RealSubject realSubject;

        @Override
        public void request() {
            if (realSubject == null) {
                realSubject = new RealSubject();  // Lazy Init
            }
            // Additional functionality before delegating the request (pre-processing)
            realSubject.request();
            // Additional functionality after delegating the request (post-processing)
        }
    }

Client

    public class Client {
        public static void main(String[] args) {
            Subject proxy = new Proxy();
            proxy.request(); // Executes the proxy request
        }
    }

---

### Examples:

1) Virutal Use-Case BookParser:

BookParser is wrapper class which loads an entire books and exposes few operations like getTotalPageNumber(), findWordUsingRegEx(), getTotalWords()....
These methods is lightweight for invoking because it is already processed during parsing time, but parsing(creating object) required lots of resources and memory.
Things is that... rarely such methods are invoked by the client, so it is good idea to initialize lazily during method call.


    public interface IBookParser {
        int getTotalNumberOfPages();
    }
    
    public class BookParser implements IBookParser{
    
        private String bookName;
    
        public BookParser(String bookName) {
            this.bookName = bookName;
        }
    
        @Override
        public int getTotalNumberOfPages() {
            return 110; // after doing some processing....
        }
    }
    
    public class Client {
    
        public static void main(String[] args) {
    
            // Without using the proxy
            BookParser bookParser = new BookParser("Harry-Potter");
            System.out.println(bookParser.getTotalNumberOfPages(););
        }
    }

Adding the Proxy:


    public class BookParserProxy implements IBookParser{
    
        String bookName;
        private BookParser bookParser;
    
        public BookParserProxy(String bookName) {
            this.bookName = bookName;
        }
    
        @Override
        public int getTotalNumberOfPages() {
    
            // lazy initialization
            if(bookParser == null) {
                bookParser = new BookParser(bookName);
            }
            return bookParser.getTotalNumberOfPages(); // actual call to real object.
        }
    }

    public class Client {
    
        public static void main(String[] args) {
    
            // Without using the proxy
            IBookParser bookParser = new BookParser("Harry-Potter"); // Here the bookParser object got created.
            System.out.println(bookParser.getTotalNumberOfPages());
    
            // With Proxy
            IBookParser bookParser1 = new BookParserProxy("Harry-Potter");
            System.out.println(bookParser.getTotalNumberOfPages()); // Here the BookParse object got created.
    
        }
    }

Security-Constraint:

    public interface IEmployee {
    
        // adding just one method to understand the concept
        String getEmployee();
    }

    public class EmployeeTable implements IEmployee{
    
        @Override
        public String getEmployee() {
            return "Mitul-Patel";
        }
    }

    public class EmployeeTableProxy implements IEmployee{
    
        String role;
        EmployeeTable employeeTable;
        
        public EmployeeTableProxy(String role) {
            this.role = role;
            // here I'm creating new Employee.... use-case is different...
            employeeTable = new EmployeeTable();
        }
    
        @Override
        public String getEmployee() {
            // adding condition before making real request to other object. 
            if(role.equals("ADMIN")) {
                return employeeTable.getEmployee();
            }
            return "Access Denied";
        }
    }

    public class Client {
    
        public static void main(String[] args) {
    
            IEmployee employee = new EmployeeTable();
            System.out.println(employee.getEmployee());
    
            IEmployee employee1 = new EmployeeTableProxy("ADMIN");
            System.out.println(employee1.getEmployee());
    
            IEmployee employee2 = new EmployeeTableProxy("USER");
            System.out.println(employee2.getEmployee());
    
        }
    }