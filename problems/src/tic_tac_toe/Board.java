package tic_tac_toe;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<String>> board;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                board.get(i).add(" ");
            }
        }
    }

    /**
     * Mark a position on the board with a given mark.
     *
     * @param x the row index of the position
     * @param y the column index of the position
     * @param mark the mark to place at the position
     */
    public void mark(int x, int y, String mark) {
        board.get(x).set(y, mark);
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board.get(i).get(j));
                if(j != 2)
                    System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public boolean isPositionValidToMark(int x, int y) {

        // out of bound
        if(x < 0 || x > 2 || y < 0 || y > 2)
            return false;

        // already marked
        if(!board.get(x).get(y).isBlank())
            return false;

        return true;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i).get(j).isBlank()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWin(int x, int y, String mark) {

        if(board.get(x).get(0).equals(mark) && board.get(x).get(1).equals(mark) && board.get(x).get(2).equals(mark))
            return true;

        if(board.get(0).get(y).equals(mark) && board.get(1).get(y).equals(mark) && board.get(2).get(y).equals(mark))
            return true;

        if(board.get(0).get(0).equals(mark) && board.get(1).get(1).equals(mark) && board.get(2).get(2).equals(mark))
            return true;

        if(board.get(0).get(2).equals(mark) && board.get(1).get(1).equals(mark) && board.get(2).get(0).equals(mark))
            return true;

        return false;
    }

}
