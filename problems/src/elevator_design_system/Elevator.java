package elevator_design_system;

import elevator_design_system.command.InternalButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elevator {

    private Integer elevatorNo;
    private InternalButton internalButton;
    private Display display;
    private Direction elevatorDirection;
    private ElevatorState elevatorState;

    public void moveLift(Direction direction, Integer floorNo) {

        // reached at specific floor, display got changed
        display.setDirection(direction);
        display.setCurrentFloor(floorNo);

        elevatorDirection = direction;
        elevatorState = ElevatorState.MOVING;

        System.out.println("Reached at: " + display.getCurrentFloor() + " We are moving: " + elevatorDirection.toString());
    }

    public void submitRequest(Integer targetFloor) {
        internalButton.dispatchRequest(targetFloor);
    }

    public Direction getReverseDirectionOfElevator() {
        if(elevatorDirection == Direction.UP) return Direction.DOWN;
        return Direction.UP;
    }
}