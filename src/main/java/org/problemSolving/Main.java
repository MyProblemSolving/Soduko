package org.problemSolving;

import org.problemSolving.models.Grid;


public class Main {
    public static void main(String[] args) {

        Grid grid = new Grid();

        grid.grid[0][0].square[0][0].number = 1;
        grid.grid[1][0].square[0][0].number = 4;
        grid.grid[2][0].square[0][0].number = 4;

        System.out.println(grid.toString());
        System.out.println(grid.isNumberValid(0,0,0,0));

    }
}