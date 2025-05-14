package snaker_ladder_game;

public class Driver {

    public static void main(String[] args) {

        SnakeLadder snakeLadder = new SnakeLadder();
        snakeLadder.registerAsPlayer("Aatman", PlayerSymbol.X);
        snakeLadder.registerAsPlayer("Hasti", PlayerSymbol.X);
        snakeLadder.startGame();
    }
}
