package tic_tac_toe;

import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private Scanner scanner;

    public TicTacToe() {
        scanner = new Scanner(System.in);
        initializeGame();
        startGame();
    }

    private void initializeGame() {
        playerX = new Player("Rohit", Symbol.X);
        playerO = new Player("Rahul", Symbol.O);
        currentPlayer = playerX;
        board = new Board();
    }

    // For multithreading application, I don't allow two players to mark same position
    private synchronized boolean markPosition(int x, int y) {
        if (board.isPositionValidToMark(x, y)) {
            board.mark(x, y, currentPlayer.getSymbol().getSign());
            return true;
        }
        System.out.printf("Invalid Position: {%d} - {%d}\n", x, y);
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == playerX) {
            currentPlayer = playerO;
        } else {
            currentPlayer = playerX;
        }
    }

    public void startGame() {
        boolean gameOver = false;
        while(!gameOver) {
            board.printBoard();
            System.out.println("Player " + currentPlayer.getName() + "'s turn");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(markPosition(x, y)) {
                if (board.isWin(x, y, currentPlayer.getSymbol().getSign())) {
                    System.out.println("Player " + currentPlayer.getName() + " wins");
                    gameOver = true;
                }
                if (board.isBoardFull()) {
                    System.out.println("Draw");
                    gameOver = true;
                }
                switchPlayer();
            }
        }
    }
}
