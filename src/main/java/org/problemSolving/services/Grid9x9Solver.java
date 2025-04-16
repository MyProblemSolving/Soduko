package org.problemSolving.services;

import lombok.AllArgsConstructor;
import org.problemSolving.models.Grid9x9;
import org.problemSolving.models.Square;

import java.util.ArrayList;
import java.util.List;

public class Grid9x9Solver {

    private final List<HistoryRecord> history = new ArrayList<>();

    public void solve(Grid9x9 gridToSolve) {

        boolean goBackInTimeAndFixHistory = false;

        int i = 0;

        int j = 0;

        int validNumber = 1;

        do {

            if(goBackInTimeAndFixHistory) {

                HistoryRecord lastRecord = history.getLast();

                i = lastRecord.i;
                j = lastRecord.j;
                validNumber = lastRecord.chosenNumber + 1;

                gridToSolve.get(i / Grid9x9.SIZE, i % Grid9x9.SIZE).get(j / Square.SIZE, j % Square.SIZE).number = 0;

                history.removeLast();

                goBackInTimeAndFixHistory = false;
            }

            gridLoop:
            while(i < Grid9x9.SIZE * Grid9x9.SIZE) {
                while( j < Square.SIZE * Square.SIZE) {

                    int xGrid = i / Grid9x9.SIZE;
                    int yGrid = i % Grid9x9.SIZE;

                    int xSquare = j / Square.SIZE;
                    int ySquare = j % Square.SIZE;

                    int number = gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number;

                    if(number == 0) {

                        while(validNumber <= 9) {

                            gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number = validNumber;

                            if(gridToSolve.isCellValid(xGrid, yGrid, xSquare, ySquare)) {
                                history.add(new HistoryRecord(i, j, validNumber));
                                break;
                            } else {
                                gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number = 0;
                            }

                            validNumber++;
                        }
                        validNumber = 1;

                    }

                    if(gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare).number == 0) {
                        goBackInTimeAndFixHistory = true;
                        break gridLoop;
                    }

                    j++;
                }
                j = 0;
                i++;
            }

        } while (goBackInTimeAndFixHistory);



    }

    @AllArgsConstructor
    static class HistoryRecord {
        int i;
        int j;
        int chosenNumber;
    }

}
