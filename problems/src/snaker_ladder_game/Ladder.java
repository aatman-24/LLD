package snaker_ladder_game;

public class Ladder extends BoardTransition {

    public Ladder(Cell from, Cell to) {
        super(from, to, BoardTransitionType.LADDER);
    }
}
