package org.problemSolving.services;

import org.problemSolving.models.Grid9x9;
import org.problemSolving.models.Square;

/*
* NOTE: this class is not finished yet
* */

public class Grid9x9Solver {

    public void solve(Grid9x9 gridToSolve) {

        for(int i = 0; i < Grid9x9.SIZE * Grid9x9.SIZE; i++) {
            for(int j = 0; j < Square.SIZE * Square.SIZE; j++) {

                int xGrid = i / Square.SIZE;
                int yGrid = i % Square.SIZE;

                int xSquare = j / Square.SIZE;
                int ySquare = j % Square.SIZE;

                int number = gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number;

                if(number != -1 && number != 0) {

                    for(int validNumber = 1; validNumber <= 9; validNumber++) {

                        gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number = validNumber;

                        if(gridToSolve.isNumberValid(xGrid, yGrid, xSquare, ySquare)) {
                            break;
                        } else {
                            gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number = -1;
                        }

                    }

                }

            }
        }

    }

}
