package snaker_ladder_game;

import java.util.*;

public class SnakeLadder {

    private List<Player> playerList;
    private Board board;
    private Map<Player, Integer> playerPosition;
    private boolean hasGameStarted;

    public SnakeLadder() {
        this.playerList = new ArrayList<>();
        this.board = new Board();
        this.playerPosition = new HashMap<>();
        this.hasGameStarted = false;
        initializeGame();
    }

    private void initializeGame() {
        // Create some blockers
        board.registerLadder(15, 50);
        board.registerLadder(10, 90);
        board.registerSnake(80, 5);
        board.registerSnake(50, 8);
    }

    public boolean registerAsPlayer(String name, PlayerSymbol playerSymbol) {
        if(hasGameStarted) return false;
        Player player = new Player(name, playerSymbol);
        playerList.add(player);
        playerPosition.put(player, 0);
        return true;
    }

    public Player startGame() {
        Queue<Player> queue = new LinkedList<>();
        for(Player player: playerList) {
            queue.add(player);
        }
        Player winner = null;

        while(!queue.isEmpty()) {

            Player currentPlayer = queue.poll();


            int diceFace = Dice.rollAndGetTopOfDice();

            Integer getCurrentPosition = playerPosition.get(currentPlayer);
            System.out.printf("Player turn: %s, position: %d\n", currentPlayer.getName(), getCurrentPosition);
            System.out.print("Rolling.....\n");
            System.out.printf("Dice face: %d\n", diceFace);

            Integer nextPosition = getCurrentPosition + diceFace;
            nextPosition = board.getNextPosition(nextPosition);
            playerPosition.put(currentPlayer, nextPosition);

            if(isWinner(currentPlayer)) {
                System.out.printf("Player is winner....::: %s", currentPlayer.getName());
                winner = currentPlayer;
                break;
            }

            queue.add(currentPlayer);
        }

        return winner;
    }

    private boolean isWinner(Player player) {
        return Objects.equals(playerPosition.get(player), board.getBoardCapacity());
    }
}
