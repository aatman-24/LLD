### Command Design Pattern(Behavioral Pattern)
---

### When to use?

> Decoupling Sender and Receiver: When you want to decouple the object that invokes the operation from the one that knows how to perform it. (Sender & Receiver)
> Supporting Undo/Redo Operations: When implementing operations that can be undone and redone.
> Supporting Transactional Operations: When operations need to be packaged into a transaction for reliability.
> Macro Commands: When you want to create a sequence of commands to be executed together.


---

### Which problem it solves?

The Command Design Pattern addresses the problem of decoupling the invoker of an operation from the object that performs the operation. By encapsulating a request as an object, it allows for:

- Queuing requests.
- Logging requests.
- Supporting undoable operations.
- Simplifying code changes and extensions.



### Standard Example:  RemoteController of Smart Home. For ex: AcRemote, LightRemote

---

### Basic template:
    
    ICommand
        execute()
        unexecute()
    
    Command
        Receiver receiver;
        execute()
        unexecute()
    
    Invoker
        command.invoke()    

    Receiver
        action()


## Code of Template:


    public interface Command {
        void execute();
    }

    public class ConcreteCommand implements Command {
        private Receiver receiver;
    
        public ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }
    
        public void execute() {
            receiver.action();
        }
    }


    public class Receiver {
        public void action() {
            // Define the action to be performed here
        }
    }

    public class Invoker {
        private Command command;
    
        public void setCommand(Command command) {
            this.command = command;
        }
    
        public void executeCommand() {
            command.execute();
        }
    }


    public class Client {
        public static void main(String[] args) {
            Receiver receiver = new Receiver();
            Command command = new ConcreteCommand(receiver);
            Invoker invoker = new Invoker();
            invoker.setCommand(command);
            invoker.executeCommand();
        }
    }




---

### How it solves?

There are three comps in this pattern:
1) Client: Who invokes a command which invokers provide.  
2) Invoker: Which have lists of commands, which can be triggered by the client. 
3) Receiver: For whom a command is executed,  Receiver performs an actual task. (for ex: turing light no)

### Example: Remote Control

Consider a remote control with two basic functions: `turnOn` and `turnOff`.

Imagine a remote control with no buttons initially. When we add a button labeled `turnOn` (which represents a command), this command abstracts the actual logic required to turn something on. When a client presses this button, it triggers the `turnOn` action. The command then sends this request to a receiver, which executes the logic to turn on the desired device.

### Logic of Undo-Redo (Execute-Unexecute)

Think of it this way: every action we perform has an opposite action, which is the undo operation. The concept is simple: we store all the actions in one place and can unexecute these actions in reverse order to revert to the previous state.

To implement undo functionality, we need to store the actions. While a stack is commonly used for this purpose, it has limitations, such as the inability to perform a redo operation. Instead, we can store the actions in an array and use a pointer to keep track of the current position. This allows us to move forward and backward through the actions, supporting both undo and redo operations.

---

### Code: Command is set before the execution. 

    public interface ICommand {
        public void execute();
        public void undo();
    }
    
    public class TurnOffCommand implements ICommand {
    
        AirConditionerReceiver airConditionerReceiver;
    
        public TurnOffCommand(AirConditionerReceiver airConditionerReceiver) {
            this.airConditionerReceiver = airConditionerReceiver;
        }
    
        @Override
        public void execute() {
            airConditionerReceiver.turnOffAC();
        }
    
        @Override
        public void undo() {
            airConditionerReceiver.turnOnAC();
        }
    }
    
    public class TurnOnCommand implements ICommand {
    
        AirConditionerReceiver airConditionerReceiver;
    
        public TurnOnCommand(AirConditionerReceiver airConditionerReceiver) {
            this.airConditionerReceiver = airConditionerReceiver;
        }
    
        @Override
        public void execute() {
            airConditionerReceiver.turnOnAC();
        }
    
    
        @Override
        public void undo() {
            airConditionerReceiver.turnOffAC();
        }
    }

    public class RemoteControlInvoker {
        
        ICommand command;
        Stack<ICommand> historyOfCommands = new Stack<>();
        

        // This command is set before client can execute it. 
        public void setCommand(ICommand command) {
            this.command = command;
        }
    
        public void pressButton() {
            this.command.execute();
            historyOfCommands.add(this.command);
        }
    
        public void undo() {
    
            if(!historyOfCommands.isEmpty()) {
                ICommand lastCommand = historyOfCommands.pop();
                lastCommand.undo();
            }
    
        }
    
    }
    
    public class CommandDesignClient {
    
        public static void run() {
             System.out.println("CommandDesignClient");
             AirConditionerReceiver airConditionerReceiver = new AirConditionerReceiver();
             RemoteControlInvoker remoteControlInvoker = new RemoteControlInvoker();
             remoteControlInvoker.setCommand(new TurnOffCommand(airConditionerReceiver));
             remoteControlInvoker.pressButton();
             remoteControlInvoker.undo();
    
        }
    }

---

## Another Example: Pre-defined command list. All set before executions. 


// Command interface
  
      public interface Command {
        void execute();
    }

// Concrete Command classes
    
    public class CopyCommand implements Command {
        private Editor editor;
    
        public CopyCommand(Editor editor) {
            this.editor = editor;
        }
    
        public void execute() {
            editor.copy();
        }
    }
    
    public class PasteCommand implements Command {
        private Editor editor;
    
        public PasteCommand(Editor editor) {
            this.editor = editor;
        }
    
        public void execute() {
            editor.paste();
        }
    }

// Receiver class
    
    public class Editor {
        public void copy() {
            System.out.println("Copying text...");
        }
    
        public void paste() {
            System.out.println("Pasting text...");
        }
    }

// Invoker class
    
    public class Application {
        private Command copyCommand;
        private Command pasteCommand;
        
        public Application(Command copyCommand, Command pasteCommand) {
            this.copyCommand = copyCommand;
            this.pasteCommand = pasteCommand;
        }
    
        public void copy() {
            copyCommand.execute();
        }
    
        public void paste() {
            pasteCommand.execute();
        }
    }

// Client
    
    public class Client {
        public static void main(String[] args) {
            Editor editor = new Editor();
            Command copy = new CopyCommand(editor);
            Command paste = new PasteCommand(editor);
        
                Application app = new Application(copy, paste);
        
                app.copy();
                app.paste();
            }
    }