package me.sadensmol.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest{

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
    public void matrix1TestWrongSize()  {
        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,44,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);
    }

    @Test(expected = Test.None.class)
    public void matrix1TestCorrectSize()  {
        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);

    }


    @Test
    public void matrix1ToStringTest()  {
        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);
        Assert.assertEquals(matrix1.toString().replaceAll("\\r\\n|\\r|\\n", ""),"[1.0, 2.0, 3.0, 4.0][5.0, 6.0, 7.0, 8.0][9.0, 10.0, 11.0, 12.0][13.0, 14.0, 15.0, 16.0]");
    }

    @Test
    public void matrix1GetElementTest()  {
        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);



        Assert.assertTrue(matrix1.getElement(0,0) == 1);
        Assert.assertTrue(matrix1.getElement(0,1) == 2);
        Assert.assertTrue(matrix1.getElement(0,2) == 3);
        Assert.assertTrue(matrix1.getElement(0,3) == 4);
    }

    @Test
    public void matrix1SetElementTest()  {
        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);

        matrix1.setElement(2,3, 55);

        Assert.assertTrue(matrix1.getElement(2,3) == 55);
    }


    @Test
    public void matrix1MulTest()  {

        Matrix1 matrix1 = new Matrix1(
                1,2,3,4,
                5,6,7,8,
                9,10,11,12,
                13,14,15,16);

        Matrix1 matrix2 = new Matrix1(
                20,21,22,23,
                24,25,26,27,
                28,29,30,31,
                32,33,34,35);



    }
}
