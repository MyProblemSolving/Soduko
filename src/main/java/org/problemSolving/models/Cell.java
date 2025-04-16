package org.problemSolving.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class Cell {
    public static final int EMPTY = 0;
    public int number = EMPTY;

    public boolean isEmpty() {
        return number == EMPTY;
    }

}
