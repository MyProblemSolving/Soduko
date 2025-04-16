package org.problemSolving.models;

import java.util.ArrayList;
import java.util.List;

public class Grid9x9 extends XYTable<Square> {

    public final static int SIZE = 3;

    public Grid9x9() {
        super(SIZE, SIZE);
        data = new Square[][] {
            {new Square(), new Square(), new Square()},
            {new Square(), new Square(), new Square()},
            {new Square(), new Square(), new Square()}
        };
    }

    public boolean isValid() {

        for(int j = 0; j < SIZE * SIZE; j++) {

            for(int i = 0; i < Square.SIZE * Square.SIZE; i++) {

                if(!isNumberValid(j/SIZE, j%SIZE, i/Square.SIZE, i%Square.SIZE)) return false;

            }

        }

        return true;

    }

    public boolean isNumberValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        boolean validRow = isRowValid(xGrid, yGrid, xSquare, ySquare);

        boolean validColumn = isColumnValid(xGrid, yGrid, xSquare, ySquare);

        return validRow && validColumn && targetedSquare.isNumberValid(xSquare, ySquare);
    }

    public boolean isRowValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        Number targetedNumber = targetedSquare.get(xSquare,ySquare);

        Square[] rowSquares = this.getRow(xGrid);

        for(Square square : rowSquares) {

            // i skip the square that the targetedNumber exists in
            if(targetedSquare != square) {

                Number[] rowNumberOfSquare = square.getRow(xSquare);

                for(Number number : rowNumberOfSquare) {
                    if (targetedNumber.number == number.number) {
                        return false;
                    }
                }

            }

        }

        return true;
    }

    public boolean isColumnValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = get(xGrid,yGrid);

        Number targetedNumber = targetedSquare.get(xSquare, ySquare);

        Square[] columnSquares = getColumn(yGrid);

        for(Square square : columnSquares) {
            // i skip the square that the targetedNumber exists in
            if(targetedSquare != square) {

                Number[] columnNumberOfSquare = square.getColumn(ySquare);

                for(Number number : columnNumberOfSquare) {
                    if (targetedNumber.number == number.number) {
                        return false;
                    }
                }

            }
        }

        return true;
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

                        Number number = square.get(currentX, currentY);

                        list.add(number.number);

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
