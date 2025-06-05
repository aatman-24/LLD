package elevator_design_system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Display {
    private Direction direction;
    private Integer currentFloor;
}
