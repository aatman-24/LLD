package snaker_ladder_game;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Dice {

    public static int rollAndGetTopOfDice() {
        Random random = new Random();
        return random.nextInt(1, 6);
    }

}
