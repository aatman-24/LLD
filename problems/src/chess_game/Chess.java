package chess_game;

import java.util.Scanner;

public class Chess {

    private Board board;
    private Player playerA;
    private Player playerB;
    private Player currentPlayer;

    public Chess() {
        board = new Board();
        playerA = new Player(Color.BLACK);
        playerB = new Player(Color.WHITE);
        currentPlayer = playerB;
        board.setupBoard(playerA, playerB);
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);

        while (isGameOver()) {

            System.out.println("Turn for: " + currentPlayer.getColor());

            // Read source position
            System.out.println("Source position (x, y): ");
            int sourceX = scanner.nextInt();
            int sourceY = scanner.nextInt();

            // Read target position
            System.out.println("Target position (x, y): ");
            int targetX = scanner.nextInt();
            int targetY = scanner.nextInt();

            Cell fromCell = board.getCell(sourceX, sourceY);
            Cell toCell = board.getCell(targetX, targetY);

            if (!board.movePiece(new Move(fromCell, toCell))) {
                System.out.println("Invalid move!!");
            } else {
                currentPlayer = (currentPlayer == playerA) ? playerB : playerA;
            }
        }
        displayResult();

    }

    private void displayResult() {
        if(board.isStatlemate(playerA) || board.isStatlemate(playerB)) {
            System.out.println("Drawwwww");
        }
        else if (board.isCheckmate(playerA)) {
            System.out.println("Winner: " + playerB.getColor());
        }
        else if (board.isCheckmate(playerB)) {
            System.out.println("Winner: " + playerA.getColor());
        }
        else {
            System.out.println("Some issue occur!");
        }
    }

    private boolean isGameOver() {
        return board.isCheckmate(playerA) || board.isCheckmate(playerB) || board.isStatlemate(playerA) || board.isStatlemate(playerB);
    }
}
