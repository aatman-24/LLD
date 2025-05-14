package snaker_ladder_game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Player {

    private Integer id;
    private String name;
    private PlayerSymbol playerSymbol;

    private static Integer count = 0;

    public Player(String name, PlayerSymbol playerSymbol) {
        this.id = ++count;
        this.name = name;
        this.playerSymbol = playerSymbol;
    }
}
