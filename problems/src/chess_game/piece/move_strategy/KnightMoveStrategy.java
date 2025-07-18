package chess_game.piece.move_strategy;

import chess_game.Board;
import chess_game.Cell;
import chess_game.Move;

public class KnightMoveStrategy implements MoveStrategy{

    @Override
    public Boolean validMove(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
