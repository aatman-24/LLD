package elevator_design_system.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalButton {

    private InternalDispatcher internalDispatcher;
    private static List<Integer> floorsButton = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public void dispatchRequest(Integer targetFloor) {
        if(floorsButton.contains(targetFloor)) {
            internalDispatcher.handleRequest(targetFloor);
        }
    }
}
