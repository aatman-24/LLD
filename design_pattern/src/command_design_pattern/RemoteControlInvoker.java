package command_design_pattern;

import
command_design_pattern.commands.ICommand;

import java.util.Stack;

public class RemoteControlInvoker {

    ICommand command;
    Stack<ICommand> historyOfCommands = new Stack<>();

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
