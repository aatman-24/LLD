package command_design_pattern.commands.impl;

import
command_design_pattern.AirConditionerReceiver;
import
command_design_pattern.commands.ICommand;

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
