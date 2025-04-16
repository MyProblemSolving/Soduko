package org.problemSolving.services;

import lombok.AllArgsConstructor;
import org.problemSolving.models.Cell;
import org.problemSolving.models.Grid9x9;
import org.problemSolving.models.Square;

import java.util.ArrayList;
import java.util.List;

public class Grid9x9Solver {

    private final List<HistoryRecord> history = new ArrayList<>();

    public void solve(Grid9x9 gridToSolve) {

        boolean goBackInTimeAndFixHistory = false;

        int gridIndex = 0;

        int squareIndex = 0;

        int chosenCellNumber = 1;

        do {

            if(goBackInTimeAndFixHistory) {

                HistoryRecord lastRecord = history.getLast();

                gridIndex = lastRecord.gridIndex;
                squareIndex = lastRecord.squareIndex;
                chosenCellNumber = lastRecord.chosenCellNumber + 1;

                gridToSolve.get(gridIndex / Grid9x9.SIZE, gridIndex % Grid9x9.SIZE).get(squareIndex / Square.SIZE, squareIndex % Square.SIZE).number = 0;

                history.removeLast();

                goBackInTimeAndFixHistory = false;
            }

            gridLoop:
            while(gridIndex < Grid9x9.SIZE * Grid9x9.SIZE) {
                while( squareIndex < Square.SIZE * Square.SIZE) {

                    int xGrid = gridIndex / Grid9x9.SIZE;
                    int yGrid = gridIndex % Grid9x9.SIZE;

                    int xSquare = squareIndex / Square.SIZE;
                    int ySquare = squareIndex % Square.SIZE;

                    Cell currentCell = gridToSolve.get(xGrid, yGrid).get(xSquare, ySquare);

                    if(currentCell.isEmpty()) {

                        while(chosenCellNumber <= 9) {

                            currentCell.number = chosenCellNumber;

                            if(gridToSolve.isCellValid(xGrid, yGrid, xSquare, ySquare)) {
                                history.add(new HistoryRecord(gridIndex, squareIndex, chosenCellNumber));
                                break;
                            } else {
                                currentCell.number = 0;
                            }

                            chosenCellNumber++;
                        }
                        chosenCellNumber = 1;

                    }

                    if(currentCell.isEmpty()) {
                        goBackInTimeAndFixHistory = true;
                        break gridLoop;
                    }

                    squareIndex++;
                }
                squareIndex = 0;
                gridIndex++;
            }

        } while (goBackInTimeAndFixHistory);

    }

    @AllArgsConstructor
    static class HistoryRecord {
        int gridIndex;
        int squareIndex;
        int chosenCellNumber;
    }

}
