package org.problemSolving.models;

public class Cell {

    public static final int EMPTY = 0;

    public int number = EMPTY;

    public int gridIndex;

    public int squareIndex;

    public Cell(int number) {
        this.number = number;
    }

    public Cell(int gridIndex, int squareIndex) {
        this.gridIndex = gridIndex;
        this.squareIndex = squareIndex;
    }

    public boolean isEmpty() {
        return number == EMPTY;
    }

}
