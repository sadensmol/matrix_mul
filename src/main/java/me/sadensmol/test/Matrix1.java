package me.sadensmol.test;

import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Matrix class
 * Represents the matrix for different matrix operations
 * It uses thread safe Vector class (slow!) to use it in
 * multithreaded application
 *
 * matrix has 0 - based values!
 * TODO - Rework it with faster collections (thread)
 *
 */

public class Matrix1 implements IMatrix{
    private Vector<Vector<Double>> matrix = new Vector<>();

    public Matrix1(double ... lines) throws IllegalArgumentException {
        double lineLength = Math.sqrt(lines.length);

        if ((lineLength % 1) != 0) throw new IllegalArgumentException("Please provide a square matrix!");


        for (int i = 0; i < lineLength; i++) {
            Vector<Double> line = new Vector<>();
            for (int j = 0; j < lineLength; j++) {
                line.add(lines[i * (int)lineLength + j]);
            }
            matrix.add(line);
        }
    }

    @Override
    //todo check for matrix size
    public IMatrix mul(IMatrix other) throws IllegalArgumentException{
        IMatrix result = new Matrix1();

        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.size(); ++j) {
                double value = 0;

                for (int i2 = 0; i2 < matrix.size(); i2++){
                    //value += get;
                }

            }
        }

        return result;
    }

    @Override
    /**
     * Set the value for element at (row,col)
     * Values are 0 based!
     */
    public void setElement(int row, int col, double value) throws IllegalArgumentException {
        matrix.get(row).set(col, value);
    }

    @Override
    public double getElement(int row, int col) throws IllegalArgumentException {
        return matrix.get(row).get(col);
    }

    @Override
    public String toString() {
        return matrix.stream().map(line -> line.toString() + "\n").collect(Collectors.joining());
    }
}
