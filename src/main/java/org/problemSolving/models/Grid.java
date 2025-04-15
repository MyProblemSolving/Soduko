package org.problemSolving.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class Grid {
    public Square[][] grid = {
            {new Square(),new Square(),new Square()},
            {new Square(),new Square(),new Square()},
            {new Square(),new Square(),new Square()}
    };

    public boolean isNumberValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = this.grid[xGrid][yGrid];

        boolean validRow = isRowValid(xGrid, yGrid, xSquare, ySquare);

        boolean validColumn = isColumnValid(xGrid, yGrid, xSquare, ySquare);

        return validRow && validColumn && targetedSquare.isNumberValid(xSquare, ySquare);
    }

    public boolean isRowValid(int xGrid, int yGrid, int xSquare, int ySquare) {

        Square targetedSquare = this.grid[xGrid][yGrid];

        Number targetedNumber = targetedSquare.square[xSquare][ySquare];

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

        Square targetedSquare = this.grid[xGrid][yGrid];

        Number targetedNumber = targetedSquare.square[xSquare][ySquare];

        Square[] columnSquares = this.getColumn(yGrid);

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

    public Square[] getRow(int x) {
        return grid[x];
    }

    public Square[] getColumn(int y) {

        List<Square> columSquares = new ArrayList<>();

        for(Square[] rowSquares : grid) {
            columSquares.add(rowSquares[y]);
        }

        return columSquares.toArray(new Square[grid.length]);
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

        for (Square[] squares : grid) {

            while (currentX < 3) {

                for (int y = 0; y < grid.length; y++) {

                    Square square = squares[y];

                    while (currentY < 3) {

                        Number number = square.square[currentX][currentY];

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
