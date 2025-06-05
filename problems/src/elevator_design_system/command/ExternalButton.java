package elevator_design_system.command;

import elevator_design_system.Direction;
import elevator_design_system.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalButton {

    private ExternalDispatcher externalDispatcher;

    public void dispatchRequest(Direction direction, Integer floorNo) {
        externalDispatcher.handleRequest(direction, floorNo);
    }
}
