package command_design_pattern.commands;

public interface ICommand {
    public void execute();
    public void undo();
}
