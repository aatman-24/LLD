Chain Of Responsibility Design Pattern (Behavioural Design Pattern)
---
### When to use?

The Chain of Responsibility Design Pattern is used when:

    You want to decouple the sender of a request from its receiver.
    You have multiple objects that can handle a request, and you want to process the request without specifying the handler explicitly.
    You want to pass a request along a chain of potential handlers until it is handled.
    The set of handlers is dynamic and can be extended without modifying existing code.

### Which problem it solves?

> The Chain of Responsibility pattern solves the problem of coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. This pattern allows you to create a chain of handler objects, where each handler processes the request or passes it to the next handler in the chain. This approach promotes flexibility and scalability by allowing new handlers to be added or existing handlers to be modified without changing the client code that sends the requests.

### Standard Template:

Handler

    abstract class Handler {
    
        protected Handler successor;
    
        public void setSuccessor(Handler successor) {
            this.successor = successor;
        }
    
        public abstract void handleRequest(Request request);
    }

Concrete Handler 1

    class ConcreteHandler1 extends Handler {
    
        public void handleRequest(Request request) {
            if (canHandle(request)) {
                // Handle the request
            } else if (successor != null) {
                successor.handleRequest(request);
            }
        }

        private boolean canHandle(Request request) {
            // Logic to determine if this handler can handle the request
        }
    }

Concrete Handler 2

    class ConcreteHandler2 extends Handler {
    
        public void handleRequest(Request request) {
            if (canHandle(request)) {
                // Handle the request
            } else if (successor != null) {
                successor.handleRequest(request);
            }
        }

        private boolean canHandle(Request request) {
            // Logic to determine if this handler can handle the request
        }
    }

Request class
    
    class Request {
        // Request details
    }

### How it solves?

    Decoupling Sender and Receiver: The pattern decouples the sender of a request from its receiver by allowing multiple objects to handle the request. Each handler in the chain is only responsible for a specific type of request or a part of the request processing.
    
    Dynamic Handling: Handlers are linked together in a chain, where each handler knows the next handler in the chain. When a handler receives a request, it can either process the request or pass it to the next handler if it cannot handle it. This dynamic handling allows the system to adapt to different requests without changing the client code.
    
    Extensibility: New handlers can be added to the chain without modifying the existing handlers or the client code. This makes the system flexible and easy to extend.
    
    Single Responsibility Principle: Each handler has a single responsibility: to handle a specific type of request or pass it along the chain. This leads to a cleaner and more maintainable codebase.

# Interview Famous Question: 1) ATM 2) Vending Machine

ATM: In ATM you have bunch of handlers who handle the withdrawal request of money. 1000NoteHandler, 500NoteHandler, 100NoteHandler
So when you withdraw some money that time it will pass through all handlers one by one until it get resolve or you see the error(out of money).

Vending Machine: Have a couple items... so each itemHave its own processor who encompasses that logic to check item and react on it otherwise pass it to next ItemHandler. 

### Standard Example: LoggerClass

    public abstract class LogProcessor {
    
        public static int INFO = 1;
        public static int DEBUG = 2;
        public static int ERROR = 3;
    
        LogProcessor nextLoggerProcessor;
    
        // Used Constructor to set the next processor we can use the setter as well.
        LogProcessor(LogProcessor loggerProcessor) {
            this.nextLoggerProcessor = loggerProcessor;
        }
    
        public void log(int logLevel, String message) {
            if (nextLoggerProcessor != null) {
                nextLoggerProcessor.log(logLevel, message);
            }
        }
    }

    public class InfoLogProcessor extends LogProcessor{
    
        InfoLogProcessor(LogProcessor nexLogProcessor){
            super(nexLogProcessor);
        }
    
        public void log(int logLevel,String message){
    
            // Only React on this
            if(logLevel == INFO) {
                System.out.println("INFO: " + message);
            } else{
                // pass to next processor/handler.
                super.log(logLevel, message);
            }
    
        }
    }
    
    public class ErrorLogProcessor extends LogProcessor{
    
        ErrorLogProcessor(LogProcessor nexLogProcessor){
            super(nexLogProcessor);
        }
    
        public void log(int logLevel,String message){
    
            if(logLevel == ERROR) {
                System.out.println("ERROR: " + message);
            } else{
    
                super.log(logLevel, message);
            }
    
        }
    }
    
    public class DebugLogProcessor extends LogProcessor{
    
        DebugLogProcessor(LogProcessor nexLogProcessor){
            super(nexLogProcessor);
        }
    
        public void log(int logLevel,String message){
    
            if(logLevel == DEBUG) {
                System.out.println("DEBUG: " + message);
            } else{
    
                super.log(logLevel, message);
            }
    
        }
    }
    
    
    public class CResponsibiltyDesignClient {
    
        public static void run() {
    
            LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
            logObject.log(LogProcessor.ERROR, "exception happens");
            logObject.log(LogProcessor.DEBUG, "need to debug this ");
            logObject.log(LogProcessor.INFO, "just for info ");
        }
    }
