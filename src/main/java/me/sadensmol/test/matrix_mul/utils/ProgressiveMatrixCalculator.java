package me.sadensmol.test.matrix_mul.utils;

import me.sadensmol.test.matrix_mul.model.IMatrix;
import me.sadensmol.test.matrix_mul.model.Matrix2;

import java.util.Deque;
import java.util.concurrent.*;

/**
 * Calculates matrix operations with threads help
 * It uses different threads, and tasks queue
 *
 * We do multiplication only for 2 matrices
 */
public class ProgressiveMatrixCalculator {

    private final IMatrix leftMatrix;
    private final IMatrix rightMatrix;

    private Deque<Future> futuresQueue = new ConcurrentLinkedDeque<>();
    private ExecutorService executor = Executors.newFixedThreadPool(12);

    public ProgressiveMatrixCalculator (IMatrix leftMatrix, IMatrix rightMatrix) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
    }

    public IMatrix mul() throws ExecutionException, InterruptedException {
        // todo - check for sizes
        int size = leftMatrix.getSize();
        final IMatrix result = new Matrix2(size);
        long l = System.currentTimeMillis();

        for (int i = 0; i < size; ++i) { //left matrix row
            for (int j = 0; j < size; ++j) { // right matrix column

                int finalI = i;
                int finalJ = j;
                futuresQueue.add(executor.submit(() ->{
                    double value = 0;

                    for (int k = 0; k < size; ++k) { //column
                        value += leftMatrix.getElement(k, finalI) * rightMatrix.getElement(finalJ, k);
                    }
                    result.setElement(finalJ, finalI, value);
                }));

            }
        }

        long l2 = System.currentTimeMillis();
        System.out.println(l2-l);


        while (!futuresQueue.isEmpty()) {
            for (Future future : futuresQueue) {
                if (future.isDone()) futuresQueue.remove(future);
            }
        }

        return result;

    }

}
