## Design Problems: Car Rental System

---

### Requirement

```tex
The game should be played on a board with numbered cells, typically with 100 cells.
The board should have a predefined set of snakes and ladders, connecting certain cells.
The game should support multiple players, each represented by a unique game piece.
Players should take turns rolling a dice to determine the number of cells to move forward.
If a player lands on a cell with the head of a snake, they should slide down to the cell with the tail of the snake.
If a player lands on a cell with the base of a ladder, they should climb up to the cell at the top of the ladder.
The game should continue until one of the players reaches the final cell on the board.
The game should handle multiple game sessions concurrently, allowing different groups of players to play independently.
```



### Identify Flow

```tex
📌 Game Flow
Initialize the SnakeLadderGame

Create a Board with predefined Snakes and Ladders

Register Players

Start the Game

Gameplay Loop

Continue until one of the players wins

Players take turns in round-robin fashion:

Roll the dice

Move the player's icon by the dice value

If the new position has a Snake or Ladder, apply the transition

Move to the next player
```

### Entity LookUp

```tex
SnakeLadder

Board

BoardTransition (abstract class for Snake & Ladder)

Cell

Snake

Ladder

Player

Symbol

Dice
```



### Relationship Lookup

```tex
SnakeLadder (1) → Board (1)

SnakeLadder (1) → Player (N)

Board (1) → Cell (N) (e.g., 100 cells)

Cell (1) → BoardTransition (0 or 1) (either snake or ladder)

Player (1) → Symbol (1)


```

### Applying SOLID Principle | Design Patterns

```tex
Ensure Single Responsibility Principle (SRP) in each class.

For example, Board should only handle board-related operations like managing snakes, ladders, and validating movements.
```

### Class Diagram

```textile
PlayerSymbol
X
O

Player
+ id: Int
+ name: String
+ symbol: PlayerSymbol

Board
+ cells: List<Cell>
--
+ registerLadder(int from, int to): boolean
+ registerSnake(int from int to): boolean
+ getNextPosition(Integer currentPosition): Integer

BoardTransition(Abstract)
- from: Cell
- to: Cell
--
+ getTarget(): Cell

Snake(BoardTransaction)
BoardTransition(BoardTransitionType.Snake)

Ladder(BoardTransaction)
BoardTransition(BoardTransitionType.Ladder)

SnakeLadder
- players: List<Player>
- board: Board
- positionOfPlayer: HashMap<Player, Integer> 
- hasGameStarted: Boolean
--
- initializeGame(): void
+ registerAsPlayer(String name, PlayerSymbol playerSymbol) : Player
+ startGame(): void
- isWinner(Player player): Boolean

Dice
- static rollAndGetTopOfDice(): int
```

### Source Code

```java
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


public enum BoardTransitionType {
    SNAKE,
    LADDER;
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BoardTransition {
    private Cell from;
    private Cell to;
    private BoardTransitionType boardTransitionType;

    public Cell getTargetCell() {
        return to;
    }
}

public class Snake extends BoardTransition {
    public Snake(Cell from, Cell to) {
        super(from, to, BoardTransitionType.SNAKE);
    }
}

public class Ladder extends BoardTransition {

    public Ladder(Cell from, Cell to) {
        super(from, to, BoardTransitionType.LADDER);
    }
}


@UtilityClass
public class Dice {

    public static int rollAndGetTopOfDice() {
        Random random = new Random();
        return random.nextInt(1, 6);
    }

}

```

```java
@Getter
@Setter
public class Board {

    private Integer boardCapacity = 100;
    private List<Cell> cellList;

    public Board() {
        cellList = new ArrayList<>(boardCapacity+1);
        for(int i = 1; i <= boardCapacity; i++) {
            cellList.add(new Cell(i));
        }
    }

    public boolean registerLadder(int from, int to) {
        Cell targetCell = cellList.get(from);
        if(!targetCell.isAnyBoardTransitionRegistered()) {
            targetCell.setBoardTransition(new Ladder(cellList.get(from), cellList.get(to)));
            return true;
        }
        return false;
    }

    public boolean registerSnake(int from, int to) {
        Cell targetCell = cellList.get(from);
        if(!targetCell.isAnyBoardTransitionRegistered()) {
            targetCell.setBoardTransition(new Snake(cellList.get(from), cellList.get(to)));
            return true;
        }
        return false;
    }

    public Integer getNextPosition(Integer currentPosition) {
        if(currentPosition >= boardCapacity) {
            System.out.println("Current Position reaching more than board capacity");
            return currentPosition;
        }
        Cell cell = cellList.get(currentPosition);
        if(cell.isAnyBoardTransitionRegistered()) {
            return cell.getTargetPosition();
        }
        return currentPosition;
    }
}


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



public class Driver {

    public static void main(String[] args) {

        SnakeLadder snakeLadder = new SnakeLadder();
        snakeLadder.registerAsPlayer("Aatman", PlayerSymbol.X);
        snakeLadder.registerAsPlayer("Hasti", PlayerSymbol.X);
        snakeLadder.startGame();
    }
}

```
