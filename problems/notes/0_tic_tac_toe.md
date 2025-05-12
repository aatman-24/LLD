## Design Problems: TicTacToe

---

### Requirement

- **Players:**
  
  - Two players take turns.
  
  - Each player has a unique symbol (e.g., 'X' and 'O').

- **Game Board:**
  
  - A 3x3 grid.
  
  - Cells can be empty or contain a symbol from a player.

- **Gameplay Flow:**
  
  - Players take turns making moves.
  
  - A move consists of placing a symbol in an empty cell.
  
  - A move is invalid if the cell is already occupied.

- **Winning Condition:**
  
  - A player wins if they place 3 of their symbols in a row, column, or diagonal.

- **Draw Condition:**
  
  - The game ends in a draw if all cells are filled and no winner exists.

- **Game End:**
  
  - Once a player wins or the game ends in a draw, no further moves are allowed.



### <u>Identify Flow</u>

```textile
We need a 3x3 board.

Players are assigned symbols: X and O.

Players take turns one by one to play.

On each turn:

The player selects a position on the board.

The position is validated.

If the position is valid → mark it on the board.

If invalid → display a message prompting the player to choose a different position.

This cycle continues until:

One player wins, or

The game ends in a draw.
```

### <u>Entity LookUp</u>



- **TicTacToe**

- **Player**

- **Symbol**

- **Board** → 3x3 grid





#### <u>Relationship Lookup</u>

```textile
TicTacToe (1) → Board (1)

TicTacToe (1) → Player (2)

Player (1) → Symbol (1)

Board (1) → Symbol (N) (String format also works)
```



#### <u>Applying SOLID Principle | Design Patterns</u>

Not applied anyting




#### <u>Class Diagram</u>

```markup-templating
TicTacToe
----
- board: Board
- playerX: Player
- playerO: Player
- currentPlayer: Player
- scanner: Scanner
-- 
+ TicTacToe(): void
+ initializeGame(): void
+ startGame(): void
- switchPlayer(): void

Board
----
- board: List<List<String>>
-- 
+ Board(): void
- initBoard(): void
+ isWin(x: int, y: int, mark: String): boolean
+ isBoardFull(): boolean

Client
----
-- 
+ main(args: String[]): void

Player
----
- name: String
- symbol: Symbol
-- 
+ Player(name: String, symbol: Symbol): void
+ getName(): String
+ getSymbol(): Symbol

Symbol
----
- sign: String
-- 
+ Symbol(sign: String): void
+ getSign(): String
```



#### <u>Code</u>

```java
public class Client {

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.startGame();
    }
}



public class Player {

    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}


public enum Symbol {
    X("X"),
    O("0");

    private final String sign;

    Symbol(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}

```

```java
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

```


