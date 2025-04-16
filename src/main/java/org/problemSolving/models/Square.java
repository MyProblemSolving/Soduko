package org.problemSolving.models;

import java.util.ArrayList;
import java.util.List;

public class Square extends XYTable<Cell> {

    public final static int SIZE = 3;

    public Square() {
        super(SIZE, SIZE);
        data = new Cell[][] {
            {new Cell(1), new Cell(2), new Cell(3)},
            {new Cell(4), new Cell(5), new Cell(6)},
            {new Cell(7), new Cell(8), new Cell(9)}
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
