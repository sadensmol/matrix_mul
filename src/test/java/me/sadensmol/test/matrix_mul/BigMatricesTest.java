package me.sadensmol.test.matrix_mul;

import me.sadensmol.test.matrix_mul.model.IMatrix;
import me.sadensmol.test.matrix_mul.model.Matrix1;
import me.sadensmol.test.matrix_mul.model.Matrix2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class BigMatricesTest {

    private long startTime;

    final int SIZE = 1000;

    @Before
    public void startTest() {
        startTime = System.currentTimeMillis();

    }

    @After
    public void stopTest() {
        System.out.println("Test time : " + (System.currentTimeMillis() - startTime));
    }


    @Test
    public void matrix1HugeMatrixTest() throws ExecutionException, InterruptedException {

        IMatrix matrix1 = new Matrix1(SIZE);
        IMatrix matrix2 = new Matrix1(SIZE);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                matrix1.setElement(j, i, new Random(100).nextDouble());
                matrix2.setElement(j, i, new Random(100).nextDouble());
            }

        matrix1.mul(matrix2);
    }

    @Test
    public void matrix2HugeMatrixTest() throws ExecutionException, InterruptedException {

        IMatrix matrix1 = new Matrix2(SIZE);
        IMatrix matrix2 = new Matrix2(SIZE);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                matrix1.setElement(j, i, new Random(100).nextDouble());
                matrix2.setElement(j, i, new Random(100).nextDouble());
            }

        matrix1.mul(matrix2);
    }

}