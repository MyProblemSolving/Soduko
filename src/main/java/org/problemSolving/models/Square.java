package org.problemSolving.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class Square {
    public Number[][] square = {
            {new Number(0),new Number(0),new Number(0)},
            {new Number(0),new Number(0),new Number(0)},
            {new Number(0),new Number(0),new Number(0)}
    };

    // Check if there is no duplication in the square
    public boolean isNumberValid(int x, int y) {
        Number targetedNumber = square[x][y];

        for (Number[] numbers : square) {

            for(Number squareNumber : numbers) {
                // squareNumber != targetedNumber: i use reference to skip the targeted number
                if(squareNumber != targetedNumber && squareNumber.number == targetedNumber.number ) {
                    return false;
                }
            }

        }

        return true;
    }

    public Number[] getRow(int x) {
        return square[x];
    }

    public Number[] getColumn(int y) {
        List<Number> columnNumbers = new ArrayList<>();

        for (Number[] rowNumbers : square) {
            columnNumbers.add(rowNumbers[y]);
        }

        return columnNumbers.toArray(new Number[square.length]);
    }

}
