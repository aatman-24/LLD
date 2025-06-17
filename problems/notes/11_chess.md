## Design Problems: Chess Game

---

### Requirement

```tex
The chess game should follow the standard rules of chess.
The game should support two players, each controlling their own set of pieces.
The game board should be represented as an 8x8 grid, with alternating black and white squares.
Each player should have 16 pieces: 1 king, 1 queen, 2 rooks, 2 bishops, 2 knights, and 8 pawns.
The game should validate legal moves for each piece and prevent illegal moves.
The game should detect checkmate and stalemate conditions.
The game should handle player turns and allow players to make moves alternately.
The game should provide a user interface for players to interact with the game.
```

### Identify Flow

```tex
Chess Board Initialization

The game starts with a chess board.

It is an N x N grid, where each cell can hold exactly one piece.

Piece Assignment

Both players are assigned their respective sets of pieces.

Game Flow

Players take turns in a round-robin fashion.

On their turn, a player selects a piece and attempts a move.

The move is validated.

If valid, the system checks for possible outcomes:

Piece capture

Checkmate

Stalemate

The game continues until:

A player's king has no legal moves (checkmate or stalemate), or

A player resigns.
```

### Entity LookUp

```tex
Board
Cell
Player
Piece
```

### Relationship Lookup

```tex
Board -> Cell (N)
Player -> Piece (N)
Cell - Piece
```



### Applying SOLID Principle | Design Patterns

```tex
1. Piece Types and Movement Strategies

* The game has multiple piece types: King, Queen, Rook, Bishop, Knight, and Pawn.
* Each piece has its own movement rules, which can be implemented using the Strategy Design Pattern.
* Define a base `MoveStrategy` interface, with concrete implementations like `KingMoveStrategy`, `QueenMoveStrategy`, `RookMoveStrategy`, `BishopMoveStrategy`, `KnightMoveStrategy`, and `PawnMoveStrategy`.
* The appropriate strategy should be assigned when a piece is created.

Alternative Approach:

* If not using the strategy pattern, movement logic can be implemented by overriding a method such as `isValidMove()` in each piece class.

2. Move Representation

* Create a POJO class named `Move` that wraps both the "from" and "to" positions of a move.

```

### Entity

```tex

Board
Cell
Player
PieceType
MoveStrategy -> KingMoveStrategy -> QueeenMoveStrategy -> RookMoveStrategy -> BishopMoveStrategy -> KnightMoveStrategy -> PawnMoveStrategy
Move -> dump object (pojo)
```



### Relationships



```tex
Board -> Cell (N)
Player -> Piece (N)
Cell - Piece
Piece - PieceType
Piece - MoveStrategy
```



### Class Diagram

```tex
Chess
- board: Board
- players: List<Player>
--
+ startGame(): void

Board
- cells: List<Cell>
-- 
- prepareBoard(): void
+ movePiece(Move move): boolean
+ getPiece(int x, int y): Piece
+ isCheckmate(Color color): boolean
+ isStalemate(Color color): boolean

Player
- name: String
- color: String

Piece(A)
- belongTo: Player
- pieceType: PieceType
- moveStaregy: MoveStrategy
--
+ validateMove(Board board, Cell from, Cell to): Boolean (A)

Cell
- x: Integer
- y: Integer
- piece: Piece

MoveStrategy
- pieceType: PieceType
--
+ isValidMove(Board board, Cell from, Cell to): Boolean

Move
- from: Cell
- to: Cell
```



### Source code

https://github.com/aatman-24/LLD/tree/main/problems/src/chess_game



This one is also good:



https://github.com/ashishps1/awesome-low-level-design/blob/main/solutions/java/src/chessgame


