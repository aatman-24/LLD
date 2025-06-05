package elevator_design_system;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElevatorCreator {

    public List<ElevatorController> elevatorControllers;
    private static ElevatorCreator instance;

    public ElevatorCreator() {
        this.elevatorControllers = new ArrayList<>(3);
    }

    public static ElevatorCreator getInstance() {
        if(instance == null) {
            instance = new ElevatorCreator();
        }
        return instance;
    }

    public void addElevatorController(ElevatorController elevatorController) {
        elevatorControllers.add(elevatorController);
    }
}