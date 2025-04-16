package org.problemSolving.models;

import java.util.ArrayList;
import java.util.List;

public class Square extends XYTable<Cell> {

    public final static int SIZE = 3;

    public Square(int gridIndex, Grid9x9 grid) {
        super(SIZE, SIZE);
        data = new Cell[][] {
            {new Cell(gridIndex,0, grid), new Cell(gridIndex,1, grid), new Cell(gridIndex,2, grid)},
            {new Cell(gridIndex,3, grid), new Cell(gridIndex,4, grid), new Cell(gridIndex,5, grid)},
            {new Cell(gridIndex,6, grid), new Cell(gridIndex,7, grid), new Cell(gridIndex,8, grid)}
        };
    }

    // Square is valid when there is no duplication in it
    public boolean isValid() {

        List<Integer> list = new ArrayList<>();

        for (Cell[] cells : data) {
            for (Cell cell : cells) {

                if (list.contains(cell.number)) {
                    return false;
                } else {
                    list.add(cell.number);
                }

            }
        }

        return true;
    }

    // Check if there is no duplication in the square
    public boolean isCellValid(int x, int y) {
        Cell targetedCell = get(x,y);

        for (Cell[] cells : data) {

            for(Cell squareCell : cells) {
                // squareNumber != targetedNumber: i use reference to skip the targeted number
                if(squareCell != targetedCell && squareCell.number == targetedCell.number ) {
                    return false;
                }
            }

        }

        return true;
    }

}
