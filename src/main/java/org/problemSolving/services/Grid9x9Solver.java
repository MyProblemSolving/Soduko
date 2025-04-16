package org.problemSolving.services;

import org.problemSolving.models.Cell;
import org.problemSolving.models.Grid9x9;
import org.problemSolving.models.Square;

import java.util.ArrayList;
import java.util.List;

public class Grid9x9Solver {

    private final List<Cell> cellHistory = new ArrayList<>();

    public void solve(Grid9x9 gridToSolve) {

        boolean goBackInTimeAndFixHistory = false;

        int gridIndex = 0;

        int squareIndex = 0;

        int chosenCellNumber = 1;

        do {

            if(goBackInTimeAndFixHistory) {

                Cell lastCellRecord = cellHistory.getLast();

                gridIndex = lastCellRecord.gridIndex;
                squareIndex = lastCellRecord.squareIndex;
                chosenCellNumber = lastCellRecord.number + 1;

                lastCellRecord.number = 0;

                cellHistory.removeLast();

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
                                cellHistory.add(currentCell);
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

}
