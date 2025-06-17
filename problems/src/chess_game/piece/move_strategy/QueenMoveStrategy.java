package chess_game.piece.move_strategy;

import chess_game.Board;
import chess_game.Cell;
import chess_game.Move;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public Boolean validMove(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == colDiff) || (from.getRow().equals(to.getRow()) || from.getCol().equals(to.getCol()));
    }
}
