package me.sadensmol.test.matrix_mul.utils;

import me.sadensmol.test.matrix_mul.model.IMatrix;
import me.sadensmol.test.matrix_mul.model.Matrix2;

import java.util.Queue;
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

    private Queue<Future<ProcessResult>> futuresQueue = new ConcurrentLinkedQueue();
    private ExecutorService executor = Executors.newFixedThreadPool(12);

    class ProcessResult {
        public int col;
        public int row;
        public double result;

        public ProcessResult(int col, int row, double result) {
            this.col = col;
            this.row = row;
            this.result = result;
        }
    }

    public ProgressiveMatrixCalculator (IMatrix leftMatrix, IMatrix rightMatrix) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
    }

    public IMatrix mul() throws ExecutionException, InterruptedException {
        // todo - check for sizes
        int size = leftMatrix.getSize();

        for (int i = 0; i < size; ++i) { //left matrix row
            for (int j = 0; j < size; ++j) { // right matrix column

                int finalI = i;
                int finalJ = j;
                futuresQueue.add(executor.submit(() ->{
                    double value = 0;

                    for (int k = 0; k < size; ++k) { //column
                        value += leftMatrix.getElement(k, finalI) * rightMatrix.getElement(finalJ, k);
                    }
                    return new ProcessResult(finalJ, finalI,value);
                }));

            }
        }


        IMatrix result = new Matrix2(size);
        while (!futuresQueue.isEmpty()) {
            for (Future<ProcessResult> future : futuresQueue) {
                if (future.isDone()) {
                    ProcessResult processResult = future.get();
                    result.setElement(processResult.col, processResult.row, processResult.result);
                    futuresQueue.remove(future);
                }

            }

        }

        return result;

    }

}
