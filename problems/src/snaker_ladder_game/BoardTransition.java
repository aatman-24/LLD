package snaker_ladder_game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BoardTransition {
    private Cell from;
    private Cell to;
    private BoardTransitionType boardTransitionType;

    public Cell getTargetCell() {
        return to;
    }
}
