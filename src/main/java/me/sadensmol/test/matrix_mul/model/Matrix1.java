package me.sadensmol.test.matrix_mul.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Matrix class
 * Represents the rect matrix for different matrix operations
 *
 * matrix has 0 - based values!
 *
 */

public class Matrix1 implements IMatrix{
    private List<List<Double>> elements = new ArrayList<List<Double>>();

    /**
     * Creates empty matrix
     *
     */
    public Matrix1(int size) throws IllegalArgumentException {
        for (int i = 0; i < size; i++) {
            List<Double> line = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                line.add(0.0);
            }
            elements.add(line);
        }
    }

    public Matrix1(double ... rows) throws IllegalArgumentException {
        double rowLength = Math.sqrt(rows.length);

        if ((rowLength % 1) != 0) throw new IllegalArgumentException("Please provide a square matrix!");


        for (int i = 0; i < rowLength; i++) {
            List<Double> line = new ArrayList<>();
            for (int j = 0; j < rowLength; j++) {
                line.add(rows[i * (int)rowLength + j]);
            }
            elements.add(line);
        }
    }

    @Override
    //todo check for matrix size
    public IMatrix mul(IMatrix other) throws IllegalArgumentException{
        IMatrix result = new Matrix1(getSize());

        for (int i = 0; i < elements.size(); ++i) { //left matrix row
            for (int j = 0; j < elements.size(); ++j) { // right matrix column
                double value = 0;

                for (int k = 0; k < elements.size(); ++k) { //column
                    value += getElement(k, i) * other.getElement(j, k);
                }
                result.setElement(j,i, value);
            }
        }

        return result;
    }

    @Override
    //todo add object check
    public boolean equals (Object object) {
        Matrix1 matrix = (Matrix1) object;
        for (int i = 0; i < matrix.getSize(); ++i)
            for (int j = 0; j < matrix.getSize(); ++j)
                if (getElement(i,j) != matrix.getElement(i,j)) return false;

                return true;
    }

    @Override
    /**
     * Set the value for element at (row,col)
     * Values are 0 based!
     */
    public void setElement(int col, int row, double value) throws IllegalArgumentException {
        elements.get(row).set(col, value);
    }

    @Override
    public double getElement(int col, int row) throws IllegalArgumentException {
        return elements.get(row).get(col);
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.stream().map(line -> line.toString() + "\n").collect(Collectors.joining());
    }
}
