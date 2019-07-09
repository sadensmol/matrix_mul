package me.sadensmol.test;

public interface IMatrix {
    IMatrix mul (IMatrix other) throws  IllegalArgumentException;;
    void setElement (int row, int col, double value) throws  IllegalArgumentException;
    double getElement (int row, int col) throws  IllegalArgumentException;

}
