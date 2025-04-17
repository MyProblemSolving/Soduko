package org.problemSolving.services;

import org.problemSolving.models.Cell;
import org.problemSolving.models.Grid9x9;

import java.util.ArrayList;
import java.util.List;

public class Grid9x9Solver {

    private final List<Cell> cellHistory = new ArrayList<>();

    public void solve(Grid9x9 gridToSolve) {

        boolean goBackInTimeAndFixHistory = false;

        int chosenCellNumber = 1;

        do {

            if(goBackInTimeAndFixHistory) {

                Cell lastCellRecord = cellHistory.getLast();

                chosenCellNumber = lastCellRecord.number + 1;

                lastCellRecord.number = Cell.EMPTY;

                cellHistory.removeLast();

                goBackInTimeAndFixHistory = false;
            }

            for(Cell cell : gridToSolve.cells) {

                if(cell.isEmpty()) {

                    boolean isAbleToChooseNumber = chooseNumberForCell(cell, chosenCellNumber);

                    if(!isAbleToChooseNumber) {
                        goBackInTimeAndFixHistory = true;
                        break;
                    }

                    chosenCellNumber = 1;
                }

            }

        } while (goBackInTimeAndFixHistory);

    }

    private boolean chooseNumberForCell(Cell cell , int startNumber) {

        for(int i = startNumber; i <= 9; i++) {

            cell.number = i;

            if(cell.isValid()) {
                cellHistory.add(cell);
                return true;
            } else {
                cell.number = Cell.EMPTY;
            }

        }

        return false;
    }

}
