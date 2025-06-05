package elevator_design_system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Building {

    List<Floor> floors = new ArrayList<>(10);

    public void addFloor(Floor floor) {
        floors.add(floor);
    }
}