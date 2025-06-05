package elevator_design_system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElevatorController {

    private Elevator elevator;

    // used when lift goes from UP -> Down
    private PriorityQueue<Integer> minPQ = new PriorityQueue<>(10);

    // used when lift goes from DOWN -> UP
    private PriorityQueue<Integer> maxPQ = new PriorityQueue<>(10, (Integer a, Integer b) -> b - a);

    // used to store the request, which can't be served at this time due to mismatch of direction
    private Queue<Integer> pendingRequest = new LinkedList<>();

    public void submitRequest(Direction direction, Integer floor) {

        PriorityQueue<Integer> sameDirectionQueue = (direction == Direction.UP) ? minPQ : maxPQ;
        PriorityQueue<Integer> oppoDirectionQueue = (direction == Direction.UP) ? maxPQ : minPQ;

        Boolean isSameDirection = elevator.getElevatorDirection().equals(direction);

        // If opposite direction, direclty add into the queue.
        if(!isSameDirection) {
            oppoDirectionQueue.add(floor);
            return;
        }

        // UP Direction
        if(direction == Direction.UP) {
            if (floor < elevator.getDisplay().getCurrentFloor()) pendingRequest.add(floor);
            else sameDirectionQueue.add(floor);
        }
        else {
            if (floor > elevator.getDisplay().getCurrentFloor()) pendingRequest.add(floor);
            else sameDirectionQueue.add(floor);
        }

        // we can call simulate
        simulate();
    }

    public void submitRequest(Integer floor) {
        Direction direction = (floor < elevator.getDisplay().getCurrentFloor()) ? Direction.DOWN : Direction.UP;
        submitRequest(direction, floor);
    }

    private void simulate() {
        // TODO: We run the simulation, with each 1 second, I call the getNextFloor() and invoke the move() method of Elevator, until Elevator become Idle.

        while(elevator.getElevatorState() != ElevatorState.IDLE) {

            try {

                Thread.sleep(1000);
                Integer nextFloor = getNextFloor();

//              // It means idle state.
                if(nextFloor == -1) break;

                elevator.moveLift(elevator.getElevatorDirection(), nextFloor);

            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }

    private Integer getNextFloor() {

        if(elevator.getElevatorDirection() == Direction.UP) {
            if(!minPQ.isEmpty()) return minPQ.poll();
        }

        if(elevator.getElevatorDirection() == Direction.DOWN) {
            if(!maxPQ.isEmpty()) return maxPQ.poll();
        }

        if(minPQ.isEmpty() && maxPQ.isEmpty() && pendingRequest.isEmpty()) {
            makeElevatorIdle();
            return -1;
        }

        return changeElevatorDirection();
    }

    private void makeElevatorIdle() {
        this.elevator.setElevatorState(ElevatorState.IDLE);
    }

    private Integer changeElevatorDirection() {

        //  current direction
        if(elevator.getElevatorDirection() == Direction.UP) {
            while(!pendingRequest.isEmpty()) {
                minPQ.add(pendingRequest.poll());
            }
        }
        if(elevator.getElevatorDirection() == Direction.DOWN) {
            while(!pendingRequest.isEmpty()) {
                maxPQ.add(pendingRequest.poll());
            }
        }

        // reverse the direction
        elevator.setElevatorDirection(elevator.getReverseDirectionOfElevator());
        return getNextFloor();
    }
}