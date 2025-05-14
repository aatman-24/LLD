package snaker_ladder_game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Cell {

    private int position;
    private BoardTransition boardTransition;

    public Cell(int position) {
        this.position = position;
        this.boardTransition = null;
    }

    public int getTargetPosition() {
        if(isAnyBoardTransitionRegistered()) {
           return this.boardTransition.getTargetCell().getPosition();
        }
        return this.position;
    }

    public boolean isAnyBoardTransitionRegistered() {
        return boardTransition != null;
    }
}
