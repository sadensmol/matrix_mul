package me.sadensmol.test.matrix_mul.model;

import java.util.concurrent.ExecutionException;

public interface IMatrix {
    IMatrix mul (IMatrix other) throws IllegalArgumentException, ExecutionException, InterruptedException;;
    void setElement (int row, int col, double value) throws  IllegalArgumentException;
    double getElement (int row, int col) throws  IllegalArgumentException;

    int getSize();
}
