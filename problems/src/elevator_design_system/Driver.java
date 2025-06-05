package elevator_design_system;

import elevator_design_system.command.ExternalButton;
import elevator_design_system.command.ExternalDispatcher;
import elevator_design_system.command.InternalButton;
import elevator_design_system.command.InternalDispatcher;

public class Driver {

    public static void main(String[] args) {

        Building building = new Building();

        Floor floor1 = new Floor(1, new ExternalButton(new ExternalDispatcher()));
        Floor floor2 = new Floor(2, new ExternalButton(new ExternalDispatcher()));
        Floor floor3 = new Floor(3, new ExternalButton(new ExternalDispatcher()));
        Floor floor4 = new Floor(4, new ExternalButton(new ExternalDispatcher()));
        Floor floor5 = new Floor(5, new ExternalButton(new ExternalDispatcher()));

        // added floors
        building.addFloor(floor1);
        building.addFloor(floor2);
        building.addFloor(floor3);
        building.addFloor(floor4);
        building.addFloor(floor5);

        InternalButton internalButtonA = new InternalButton(new InternalDispatcher());
        Display displayA = new Display(Direction.UP, 1);
        Elevator elevatorA = new Elevator(1, internalButtonA, displayA, Direction.UP, ElevatorState.IDLE);

        InternalButton internalButtonB = new InternalButton(new InternalDispatcher());
        Display displayB = new Display(Direction.DOWN, 5);
        Elevator elevatorB = new Elevator(1, internalButtonB, displayB, Direction.UP, ElevatorState.IDLE);

        // Added elevator and its controller
        ElevatorCreator elevatorCreator = ElevatorCreator.getInstance();
        elevatorCreator.addElevatorController(new ElevatorController(elevatorA));
        elevatorCreator.addElevatorController(new ElevatorController(elevatorB));

        // Actual working start from here,
        floor2.requestElevator(Direction.UP);


    }
}
