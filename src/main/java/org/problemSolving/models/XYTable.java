package org.problemSolving.models;

public class XYTable<DATA> {

    public DATA[][] data;

    public int numberOfRows;

    public int numberOfColumns;

    protected XYTable(int numberOfRows, int numberOfColumns) {

        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        this.data = (DATA[][]) new Object[numberOfRows][numberOfColumns];
    }

    public DATA get(int row, int column) {
        return data[row][column];
    }

    public DATA[] getRow(int x) {
        return data[x];
    }

    public DATA[] getColumn(int y) {

        DATA[] columns = (DATA[]) new Object[numberOfRows];

        for (int row = 0; row < numberOfRows; row++) {
            columns[row] = data[row][y];
        }

        return columns;
    }

}
