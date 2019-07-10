package me.sadensmol.test.matrix_mul;

import me.sadensmol.test.matrix_mul.model.IMatrix;
import me.sadensmol.test.matrix_mul.model.Matrix1;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Matrix1Test {

    private long startTime;

    @Before
    public void startTest() {
        startTime = System.currentTimeMillis();

    }

    @After
    public void stopTest() {
        System.out.println("Test time : " + (System.currentTimeMillis() - startTime));
    }

    @Test(expected = IllegalArgumentException.class)
    public void matrix1TestWrongSize() {
        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4, 44,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);
    }

    @Test(expected = Test.None.class)
    public void matrix1TestCorrectSize() {
        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);

    }


    @Test
    public void matrix1ToStringTest() {
        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);
        Assert.assertEquals(matrix1.toString().replaceAll("\\r\\n|\\r|\\n", ""), "[1.0, 2.0, 3.0, 4.0][5.0, 6.0, 7.0, 8.0][9.0, 10.0, 11.0, 12.0][13.0, 14.0, 15.0, 16.0]");
    }

    @Test
    public void matrix1GetElementTest() {
        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);


        Assert.assertTrue(matrix1.getElement(0, 0) == 1);
        Assert.assertTrue(matrix1.getElement(0, 1) == 5);
        Assert.assertTrue(matrix1.getElement(3, 2) == 12);
        Assert.assertTrue(matrix1.getElement(1, 3) == 14);
    }

    @Test
    public void matrix1SetElementTest() {
        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);

        matrix1.setElement(2, 3, 55);

        Assert.assertTrue(matrix1.getElement(2, 3) == 55);
    }


    @Test
    public void matrix1MulTest() throws ExecutionException, InterruptedException {

        IMatrix matrix1 = new Matrix1(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16);

        IMatrix matrix2 = new Matrix1(
                20, 21, 22, 23,
                24, 25, 26, 27,
                28, 29, 30, 31,
                32, 33, 34, 35);


        IMatrix result = new Matrix1(
                1 * 20 + 2 * 24 + 3 * 28 + 4 * 32, 1 * 21 + 2 * 25 + 3 * 29 + 4 * 33, 1 * 22 + 2 * 26 + 3 * 30 + 4 * 34, 1 * 23 + 2 * 27 + 3 * 31 + 4 * 35,
                5 * 20 + 6 * 24 + 7 * 28 + 8 * 32, 5 * 21 + 6 * 25 + 7 * 29 + 8 * 33, 5 * 22 + 6 * 26 + 7 * 30 + 8 * 34, 5 * 23 + 6 * 27 + 7 * 31 + 8 * 35,
                9 * 20 + 10 * 24 + 11 * 28 + 12 * 32, 9 * 21 + 10 * 25 + 11 * 29 + 12 * 33, 9 * 22 + 10 * 26 + 11 * 30 + 12 * 34, 9 * 23 + 10 * 27 + 11 * 31 + 12 * 35,
                13 * 20 + 14 * 24 + 15 * 28 + 16 * 32, 13 * 21 + 14 * 25 + 15 * 29 + 16 * 33, 13 * 22 + 14 * 26 + 15 * 30 + 16 * 34, 13 * 23 + 14 * 27 + 15 * 31 + 16 * 35);

        Assert.assertTrue(matrix1.mul(matrix2).equals(result));
    }

    @Test
    public void matrix1HugeMatrixTest() throws ExecutionException, InterruptedException {

        int SIZE = 200;

        IMatrix matrix1 = new Matrix1(SIZE);
        IMatrix matrix2 = new Matrix1(SIZE);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                matrix1.setElement(j, i, new Random(100).nextDouble());
                matrix2.setElement(j, i, new Random(100).nextDouble());
            }

        matrix1.mul(matrix2);
    }
}