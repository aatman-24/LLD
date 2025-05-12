Singleton Design Pattern(Creational Pattern)
---

> > A class has only one instance and provides a global point of access to that resource. 

---

### When to use?

> When exactly one instance of a class is needed: If you have a class that controls access to a resource, such as a database connection or a file, and you want to ensure that there is only one instance of the class.
> When the single instance should be accessible globally: You want to provide a global point of access to that instance, which can be used by multiple parts of the application.
> When controlling concurrent access to a shared resource: This is useful in multi-threaded applications where it's crucial to ensure that only one thread can access the resource at a time.

### Standard Template:

    public class Singleton {
        // Private static variable to hold the single instance
        private static Singleton instance;
    
        // Private constructor to prevent instantiation
        private Singleton() {}
    
        // Public method to provide access to the instance
        public static Singleton getInstance() {
            if (instance == null) {
                // Lazy initialization
                instance = new Singleton();
            }
            return instance;
        }
    
        public void showMessage(){
            System.out.println("Hello World!");
        }
    }

### How it solves?

> Single Instance: The private static variable instance holds the single instance of the class. The instance is created only once, when it is first needed.
> Global Access Point: The getInstance() method provides a global point of access to the instance. No matter how many times this method is called, it will always return the same instance.
> Lazy Initialization: The instance is created only when it is first requested by a call to getInstance(). This ensures that the instance is not created until it is actually needed.

### Example of DbConnection:

    public class DatabaseConnection {
    
        private String dbHost;
        private String dbUsrname;
        private String dbPassword;
    
        private static DatabaseConnection databaseConnection;
    
        // constructor is private. We don't provide access to outside, so they can't create object of this class. Smart!!
        private DatabaseConnection(String dbHost, String dbUsrname, String dbPassword) {
            this.dbHost = dbHost;
            this.dbUsrname = dbUsrname;
            this.dbPassword = dbPassword;
        }
    
        public static DatabaseConnection getInstance() {
    
            // If databaseConnection is not created previously, then and only we create first object.
            if(databaseConnection == null) {
                databaseConnection = new DatabaseConnection("127.0.0.1", "aatman", "psswd");
                return databaseConnection;
            }
    
            // Otherwise, we return first object everytime.
            return  databaseConnection;
        }
    
        public String getDbHost() {
            return dbHost;
        }
    
        public String getDbUsrname() {
            return dbUsrname;
        }
    
        public String getDbPassword() {
            return dbPassword;
        }
    }
    
    public class SingletonDesignClient {
    
        public static void run() {
    
            System.out.println("SingletonDesignClient");
    
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            System.out.println(databaseConnection.getDbUsrname());
    
        }
    }