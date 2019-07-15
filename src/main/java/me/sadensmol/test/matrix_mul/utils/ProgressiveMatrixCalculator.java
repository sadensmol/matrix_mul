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

    private static final int NUM_THREADS = 12;

    private final IMatrix leftMatrix;
    private final IMatrix rightMatrix;

    private Deque<Future> futuresQueue = new ConcurrentLinkedDeque<>();
    private ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    public ProgressiveMatrixCalculator (IMatrix leftMatrix, IMatrix rightMatrix) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
    }

    public IMatrix mul() throws ExecutionException, InterruptedException {
        // todo - check for sizes
        int size = leftMatrix.getSize();
        final IMatrix result = new Matrix2(size);

        int stepSize = size / NUM_THREADS;
        if (stepSize == 0) stepSize = 1;

        for (int i = 0; i<= NUM_THREADS; i++) {
            int startIndex = i *  stepSize;
            if (startIndex >= size) break;

            int endIndex = startIndex + stepSize;
            if (endIndex >= size ) endIndex = size;

            futuresQueue.add(executor.submit(new CalculateWorker(result, startIndex, endIndex)));
        }

        while (!futuresQueue.isEmpty()) {
            for (Future future : futuresQueue) {
                if (future.isDone()) futuresQueue.remove(future);
            }
        }

        return result;
    }

    class CalculateWorker implements Callable<Void>{
        IMatrix matrix;
        int startIndex;
        int endIndex;

        public CalculateWorker (IMatrix matrix, int startIndex, int endIndex) {
            this.matrix = matrix;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public Void call() throws Exception {

            for (int i = startIndex; i < endIndex; ++i) { //left matrix row
                for (int j = 0; j < matrix.getSize(); ++j) { // right matrix column

                    double value = 0;
                    for (int k = 0; k < matrix.getSize(); ++k) { //number
                        value += leftMatrix.getElement(k, i) * rightMatrix.getElement(j, k);
                    }
                    matrix.setElement(j, i, value);
                }
            }

            return null;
        }
    }
}
