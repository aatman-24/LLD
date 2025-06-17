package chess_game.piece.pieces;

import chess_game.Board;
import chess_game.Move;
import chess_game.Player;
import chess_game.piece.move_strategy.MoveStrategy;
import chess_game.piece.move_strategy.PieceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    private Player belongTo;
    private PieceType type;
    private MoveStrategy moveStrategy;

    public Boolean validateMove(Board board, Move move) {
        return moveStrategy.validMove(board, move.getFromCell(), move.getToCell());
    }
}
