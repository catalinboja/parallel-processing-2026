package ro.ase.ie.paralel;

// Unreliable benchmarking
// Problem: Measuring thread creation rather than the actual computation can lead to misleading performance conclusions.
// Fix: Move the end timestamp after worker.join().

public class BenchmarkProblem {
    public static void main(String[] args)
            throws InterruptedException {

        long start = System.nanoTime();

        Thread worker = new Thread(() -> {
            long sum = 0;

            for (int i = 0; i < 10_000_000; i++) {
                sum += i;
            }

            System.out.println("Sum: " + sum);
        });

        worker.start();

        long end = System.nanoTime();

        System.out.println(
            "Measured time: " + (end - start) / 1_000_000 + " ms"
        );

        worker.join();
    }
}
