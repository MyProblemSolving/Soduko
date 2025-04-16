package org.problemSolving.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid9x9 extends XYTable<Square> {

    public final static int SIZE = 3;

    public final List<Cell> cells;

    public Grid9x9() {
        super(SIZE, SIZE);
        this.data = new Square[][] {
            {new Square(0,this), new Square(1,this), new Square(2,this)},
            {new Square(3,this), new Square(4,this), new Square(5,this)},
            {new Square(6,this), new Square(7,this), new Square(8,this)}
        };
        this.cells = Collections.unmodifiableList(getAllCells());
    }

    public boolean isValid() {

        for(Cell cell : cells) {
            if(!cell.isValid()) return false;
        }

        return true;
    }

    public boolean isCellValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        boolean validRow = isRowValid(xGrid, yGrid, xSquare, ySquare);

        boolean validColumn = isColumnValid(xGrid, yGrid, xSquare, ySquare);

        return validRow && validColumn && targetedSquare.isCellValid(xSquare, ySquare);
    }

    private boolean isRowValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        Cell targetedCell = targetedSquare.get(xSquare,ySquare);

        Square[] rowSquares = this.getRow(xGrid);

        for(Square square : rowSquares) {

            // i skip the square that the targetedNumber exists in
            if(targetedSquare != square) {

                Cell[] rowSquare = square.getRow(xSquare);

                for(Cell cell : rowSquare) {
                    if (targetedCell.number == cell.number) {
                        return false;
                    }
                }

            }

        }

        return true;
    }

    private boolean isColumnValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        Cell targetedCell = targetedSquare.get(xSquare, ySquare);

        Square[] columnSquares = getColumn(yGrid);

        for(Square square : columnSquares) {
            // i skip the square that the targetedNumber exists in
            if(targetedSquare != square) {

                Cell[] columnCellOfSquare = square.getColumn(ySquare);

                for(Cell cell : columnCellOfSquare) {
                    if (targetedCell.number == cell.number) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    private List<Cell> getAllCells() {

        List<Cell> list = new ArrayList<>();

        for(int j = 0; j < SIZE * SIZE; j++) {
            for(int i = 0; i < Square.SIZE * Square.SIZE; i++) {
                list.add(get(j/SIZE,j%SIZE).get(i/Square.SIZE,i%Square.SIZE));
            }
        }

        return list;
    }

    @Override
    public String toString(){

        String template = """
                -----------------------------
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                -----------------------------
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                -----------------------------
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                [%d][%d][%d],[%d][%d][%d],[%d][%d][%d]
                -----------------------------
                """;

        List<Integer> list = new ArrayList<>();

        int currentX = 0;
        int currentY = 0;

        for (Square[] squares : data) {

            while (currentX < Square.SIZE) {

                for (int y = 0; y < Square.SIZE; y++) {

                    Square square = squares[y];

                    while (currentY < Square.SIZE) {

                        Cell cell = square.get(currentX, currentY);

                        list.add(cell.number);

                        currentY++;

                    }

                    currentY = 0;

                }

                currentX++;
            }

            currentX = 0;

        }

        return String.format(template, list.toArray());

    }
}
