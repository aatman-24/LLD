package snaker_ladder_game;

import lombok.Getter;
import lombok.val;

@Getter
public enum PlayerSymbol {

    X("X"),
    Y("Y"),
    O("O");

    private String value;

    PlayerSymbol(String value) {
        this.value = value;
    }
}
