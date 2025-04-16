package org.problemSolving.models;

public class Cell {

    public static final int EMPTY = 0;

    public int number = EMPTY;

    public int gridIndex;

    public int squareIndex;

    public final Grid9x9 grid;

    public Cell(int gridIndex, int squareIndex, Grid9x9 grid) {
        this.grid = grid;
        this.gridIndex = gridIndex;
        this.squareIndex = squareIndex;
    }

    public boolean isEmpty() {
        return number == EMPTY;
    }

}
