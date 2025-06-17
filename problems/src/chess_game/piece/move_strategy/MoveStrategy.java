package chess_game.piece.move_strategy;

import chess_game.Board;
import chess_game.Cell;
import chess_game.Move;

public interface MoveStrategy {
    Boolean validMove(Board board, Cell from, Cell to);
}
