package chess_game;

import chess_game.piece.move_strategy.*;
import chess_game.piece.pieces.Piece;
import lombok.Data;

import java.util.Objects;

@Data
public class Board {

    private static final Integer N = 8;
    private Cell[][] board;

    public Board() {
        board = new Cell[N][N];
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        board[row][col] = cell;
    }

    public void setupBoard(Player playerX, Player playerY) {

        // First Add N * N Cells
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        // Add Pawns
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();
        for (int j = 0; j < N; j++) {
            this.getCell(1, j).setPiece(new Piece(playerX, PieceType.PAWN, pawnMoveStrategy));
            this.getCell(6, j).setPiece(new Piece(playerY, PieceType.PAWN, pawnMoveStrategy));
        }

        // Add Rooks
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();
        this.getCell(0, 0).setPiece(new Piece(playerX, PieceType.ROOK, rookMoveStrategy));
        this.getCell(0, 7).setPiece(new Piece(playerX, PieceType.ROOK, rookMoveStrategy));
        this.getCell(7, 0).setPiece(new Piece(playerY, PieceType.ROOK, rookMoveStrategy));
        this.getCell(7, 7).setPiece(new Piece(playerY, PieceType.ROOK, rookMoveStrategy));

        // Add Knights
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();
        this.getCell(0, 1).setPiece(new Piece(playerX, PieceType.KNIGHT, knightMoveStrategy));
        this.getCell(0, 6).setPiece(new Piece(playerX, PieceType.KNIGHT, knightMoveStrategy));
        this.getCell(7, 1).setPiece(new Piece(playerY, PieceType.KNIGHT, knightMoveStrategy));
        this.getCell(7, 6).setPiece(new Piece(playerY, PieceType.KNIGHT, knightMoveStrategy));

        // Add Bishops
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();
        this.getCell(0, 2).setPiece(new Piece(playerX, PieceType.BISHOP, bishopMoveStrategy));
        this.getCell(0, 5).setPiece(new Piece(playerX, PieceType.BISHOP, bishopMoveStrategy));
        this.getCell(7, 2).setPiece(new Piece(playerY, PieceType.BISHOP, bishopMoveStrategy));
        this.getCell(7, 5).setPiece(new Piece(playerY, PieceType.BISHOP, bishopMoveStrategy));

        // Add Queens
        QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy();
        this.getCell(0, 3).setPiece(new Piece(playerX, PieceType.QUEEN, queenMoveStrategy));
        this.getCell(7, 3).setPiece(new Piece(playerY, PieceType.QUEEN, queenMoveStrategy));

        // Add Kings
        KingMoveStrategy kingMoveStrategy = new KingMoveStrategy();
        this.getCell(0, 4).setPiece(new Piece(playerX, PieceType.KING, kingMoveStrategy));
        this.getCell(7, 4).setPiece(new Piece(playerY, PieceType.KING, kingMoveStrategy));
    }

    public Boolean movePiece(Move move) {
        Cell from = move.getFromCell();
        Cell to = move.getToCell();

        Piece piece = from.getPiece();
        if (Objects.isNull(piece) || !piece.validateMove(this, move)) return false;

        to.setPiece(piece);
        from.setPiece(null);    // here; if there is any other piece, it got killed.
        return true;
    }

    public Boolean isCheckmate(Player player) {
        // TODO:
        return false;
    }

    public Boolean isStatlemate(Player player) {
        // TODO:
        return false;
    }

}
