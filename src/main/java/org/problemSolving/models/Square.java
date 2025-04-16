package org.problemSolving.models;

import java.util.ArrayList;
import java.util.List;

public class Square extends XYTable<Number> {

    private final static int SIZE = 3;

    public Square() {
        super(SIZE, SIZE);
    }

    // Square is valid when there is no duplication in it
    public boolean isValid() {

        List<Integer> list = new ArrayList<>();

        for (Number[] numbers : data) {
            for (Number number : numbers) {

                if (list.contains(number.number)) {
                    return false;
                } else {
                    list.add(number.number);
                }

            }
        }

        return true;
    }

    // Check if there is no duplication in the square
    public boolean isNumberValid(int x, int y) {
        Number targetedNumber = get(x,y);

        for (Number[] numbers : data) {

            for(Number squareNumber : numbers) {
                // squareNumber != targetedNumber: i use reference to skip the targeted number
                if(squareNumber != targetedNumber && squareNumber.number == targetedNumber.number ) {
                    return false;
                }
            }

        }

        return true;
    }

}
