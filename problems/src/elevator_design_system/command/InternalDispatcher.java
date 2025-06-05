package elevator_design_system.command;

import elevator_design_system.Direction;
import elevator_design_system.ElevatorController;
import elevator_design_system.ElevatorCreator;

import java.util.List;

public class InternalDispatcher {

    public void handleRequest(Integer floor) {

        ElevatorCreator elevatorCreator = ElevatorCreator.getInstance();
        List<ElevatorController> elevatorControllers = elevatorCreator.getElevatorControllers();

        // I'm using that odd/even logic for sending the request to elevator controller
        for(ElevatorController elevatorController: elevatorControllers) {

            if(elevatorController.getElevator().getElevatorNo() % 2 == 1 && floor % 2 == 1) {
                elevatorController.submitRequest(floor);
                return;
            }
            if(elevatorController.getElevator().getElevatorNo() % 2 == 0 && floor % 2 == 0) {
                elevatorController.submitRequest(floor);
                return;
            }
        }
    }
}
