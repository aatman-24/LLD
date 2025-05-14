package snaker_ladder_game;

public class Snake extends BoardTransition {
    public Snake(Cell from, Cell to) {
        super(from, to, BoardTransitionType.SNAKE);
    }
}
