package alg.ext01;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Array {
    private final long[] a;

    public Array(int size) {
        a = new long[size];
    }

    public void set(int index, long value) {
        a[index] = value;
    }

    public boolean isSorted() {
        if (a.length <= 1) return true;

        for (int i = 0; i < a.length - 2; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void bubbleSort() {
        for (int out = a.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) {
                    long temp = a[in];
                    a[in] = a[in + 1];
                    a[in + 1] = temp;
                }
            }
        }
    }

    public void selectionSort() {
        for (int out = 0; out < a.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < a.length; in++) {
                if (a[in] < a[min]) {
                    min = in;
                }
            }
            long temp = a[out];
            a[out] = a[min];
            a[min] = temp;
        }
    }

    public void insertionSort() {
        for (int out = 1; out < a.length; out++) {
            long temp = a[out];
            int in = out;
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
    }

    public void oddEvenSort() {
        boolean wasSwap;
        do {
            wasSwap = false;
            for (int i = 1; i < a.length - 1; i += 2) {
                if (a[i] > a[i + 1]) {
                    long temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    wasSwap = true;
                }
            }
            for (int i = 0; i < a.length - 1; i += 2) {
                if (a[i] > a[i + 1]) {
                    long temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    wasSwap = true;
                }
            }
        } while (wasSwap);
    }

    public void parallelOddEvenSort() {
        int cpuThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cpuThreads);

        try {
            var barrier = new CyclicBarrier(cpuThreads);
            var swapped = new AtomicInteger(0);
            for (int i = 0; i < cpuThreads; i++) {
                executor.submit(new OddEvenSort(barrier, swapped, i, cpuThreads));
            }
            executor.shutdown();

            if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class OddEvenSort implements Runnable {
        private final CyclicBarrier barrier;
        private final AtomicInteger swapped;
        private final int iThread;
        private final int threads;

        OddEvenSort(CyclicBarrier barrier, AtomicInteger swapped, int iThread, int threads) {
            this.barrier = barrier;
            this.swapped = swapped;
            this.iThread = iThread;
            this.threads = threads;
        }

        @Override
        public void run() {
            try {
                int prevSwapped = -1;
                while (prevSwapped != swapped.get()) {
                    prevSwapped = swapped.get();
                    barrier.await();

                    if (oddEvenSort()) {
                        swapped.incrementAndGet();
                    }

                    barrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }

        private boolean oddEvenSort() throws InterruptedException, BrokenBarrierException {
            boolean wasSwap = false;

            for (int i = 2 * iThread; i < a.length - 1; i += 2 * threads) {
                if (a[i] > a[i + 1]) {
                    long temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    wasSwap = true;
                }
            }

            barrier.await();
            for (int i = 2 * iThread + 1; i < a.length - 1; i += 2 * threads) {
                if (a[i] > a[i + 1]) {
                    long temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    wasSwap = true;
                }
            }

            return wasSwap;
        }
    }

    @Override
    public String toString() {
        final int maxSize = 100;

        if (a.length <= maxSize) {
            return Arrays.toString(a);
        }

        var b = new long[maxSize];
        System.arraycopy(a, 0, b, 0, maxSize);
        return "First " + maxSize + " elements " + Arrays.toString(b);
    }
}
