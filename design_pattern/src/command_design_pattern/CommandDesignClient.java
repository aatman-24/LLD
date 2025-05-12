package command_design_pattern;

import
command_design_pattern.commands.impl.TurnOffCommand;
import
command_design_pattern.commands.impl.TurnOnCommand;
import
strategy_design_pattern.WithPattern.OffRoadVehicle;
import
strategy_design_pattern.WithPattern.PassengerVehicle;
import
strategy_design_pattern.WithPattern.SportsVehicle;
import
strategy_design_pattern.WithPattern.Vehicle;

import java.util.ArrayList;
import java.util.List;

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
