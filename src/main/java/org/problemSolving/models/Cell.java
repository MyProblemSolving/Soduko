package org.problemSolving.models;

public class Cell {

    public static final int EMPTY = 0;

    public int number = EMPTY;

    private final Grid9x9 grid;

    public final int gridIndex;
    public final int squareIndex;

    public final int xGrid;
    public final int yGrid;
    public final int xSquare;
    public final int ySquare;

    public Cell(int gridIndex, int squareIndex, Grid9x9 grid) {

        this.grid = grid;

        this.gridIndex = gridIndex;
        this.squareIndex = squareIndex;

        this.xGrid = gridIndex / Grid9x9.SIZE;
        this.yGrid = gridIndex % Grid9x9.SIZE;
        this.xSquare = squareIndex / Square.SIZE;
        this.ySquare = squareIndex % Square.SIZE;
    }

    public boolean isEmpty() {
        return number == EMPTY;
    }

    public boolean isValid() {
        return number != EMPTY && grid.isCellValid(xGrid, yGrid, xSquare, ySquare);
    }

}
