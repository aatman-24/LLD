package chess_game;

import chess_game.piece.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cell {

    private Integer row, col;
    private Piece piece;

    public Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }
}
