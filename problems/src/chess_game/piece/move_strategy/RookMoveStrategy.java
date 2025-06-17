package chess_game.piece.move_strategy;

import chess_game.Board;
import chess_game.Cell;
import chess_game.Move;

import java.util.Objects;

public class RookMoveStrategy implements MoveStrategy{

    @Override
    public Boolean validMove(Board board, Cell from, Cell to) {
        return (Objects.equals(from.getRow(), to.getRow()) || Objects.equals(from.getCol(), to.getCol()));
    }
}
