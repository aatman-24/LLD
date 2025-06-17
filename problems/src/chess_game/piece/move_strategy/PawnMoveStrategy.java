package chess_game.piece.move_strategy;

import chess_game.Board;
import chess_game.Cell;
import chess_game.Color;
import chess_game.Move;

public class PawnMoveStrategy implements MoveStrategy{

    @Override
    public Boolean validMove(Board board, Cell from, Cell to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        Color color = from.getPiece().getBelongTo().getColor();

        if (color == Color.WHITE) {
            return (rowDiff == 1 && colDiff == 0) ||
                    (from.getRow() == 1 && rowDiff == 2 && colDiff == 0) ||
                    (rowDiff == 1 && colDiff == 1 && board.getCell(to.getRow(), to.getCol()).getPiece() != null);
        } else {
            return (rowDiff == -1 && colDiff == 0) ||
                    (from.getRow() == 6 && rowDiff == -2 && colDiff == 0) ||
                    (rowDiff == -1 && colDiff == 1 && board.getCell(to.getRow(), to.getCol()).getPiece() != null);
        }
    }
}
