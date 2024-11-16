package command_design_pattern.commands.impl;

import
command_design_pattern.AirConditionerReceiver;
import
command_design_pattern.commands.ICommand;

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
