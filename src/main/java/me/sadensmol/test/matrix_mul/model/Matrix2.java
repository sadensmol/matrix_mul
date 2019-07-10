package me.sadensmol.test.matrix_mul.model;

import me.sadensmol.test.matrix_mul.utils.ProgressiveMatrixCalculator;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Matrix class
 * Represents the rect matrix for different matrix operations
 * It uses thread safe Vector class (slow!) but synchronized :)
 *
 * matrix has 0 - based values!
 * TODO - Rework it with faster collections (thread safe like CopyOnWrite)
 *
 */

public class Matrix2 implements IMatrix{
    private List<List<Double>> elements = new CopyOnWriteArrayList<>();

    /**
     * Creates empty matrix
     *
     */
    public Matrix2(int size) throws IllegalArgumentException {
        for (int i = 0; i < size; i++) {
            Vector<Double> line = new Vector<>();
            for (int j = 0; j < size; j++) {
                line.add(0.0);
            }
            elements.add(line);
        }
    }

    public Matrix2(double ... rows) throws IllegalArgumentException {
        double rowLength = Math.sqrt(rows.length);

        if ((rowLength % 1) != 0) throw new IllegalArgumentException("Please provide a square matrix!");


        for (int i = 0; i < rowLength; i++) {
            Vector<Double> line = new Vector<>();
            for (int j = 0; j < rowLength; j++) {
                line.add(rows[i * (int)rowLength + j]);
            }
            elements.add(line);
        }
    }

    @Override
    /**
     * @throws IllegalArgumentException - when the matrixes have wrong sizes
     * @throws ExecutionException, InterruptedException - when multiplty process failed for some reason
     */
    //todo check for matrix size
    public IMatrix mul(IMatrix other) throws IllegalArgumentException, ExecutionException, InterruptedException {
        ProgressiveMatrixCalculator calculator = new ProgressiveMatrixCalculator(this, other);
        return calculator.mul();
    }

    @Override
    //todo add object check
    public boolean equals (Object object) {
        Matrix2 matrix = (Matrix2) object;
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
