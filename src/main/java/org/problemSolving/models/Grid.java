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

        for (Square[] squares : grid) {
            for (Square square : squares) {

                for(Number[] numbers : square.square) {
                    for (Number number : numbers) {
                        list.add(number.number);
                    }
                }

            }
        }


        return String.format(template, list.toArray());

    }
}
