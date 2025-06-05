package elevator_design_system;

import elevator_design_system.command.ExternalButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Floor {

    private Integer floorNo;
    private ExternalButton externalButton;

    public void requestElevator(Direction direction) {
        externalButton.dispatchRequest(direction, floorNo);
    }
}