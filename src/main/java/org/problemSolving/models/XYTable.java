package org.problemSolving.models;

import java.lang.reflect.Array;

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

        Object[] columns = new Object[numberOfRows];

        for (int row = 0; row < numberOfRows; row++) {
            columns[row] = data[row][y];
        }

        return (DATA[]) convertFromObjectArray(data[0][0].getClass(), columns);
    }

    private <T> T[] convertFromObjectArray(Class<T> clazz, Object[] objArray) {

        T[] targetArray = (T[]) Array.newInstance(clazz, objArray.length);

        for (int i = 0; i < objArray.length; i++) {
            if (clazz.isInstance(objArray[i])) {
                targetArray[i] = clazz.cast(objArray[i]);
            } else {
                throw new ClassCastException("Element #" + i + ": Cannot cast " + objArray[i].getClass()
                        .getName() + " to " + clazz.getName());
            }
        }

        return targetArray;
    }

}
