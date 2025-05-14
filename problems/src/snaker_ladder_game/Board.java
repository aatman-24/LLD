package snaker_ladder_game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
