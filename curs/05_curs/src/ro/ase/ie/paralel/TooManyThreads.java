package ro.ase.ie.paralel;

// Thread oversubscription
// Problem: Creating more CPU-bound threads than available cores can increase overhead without improving performance.

public class TooManyThreads {
    public static void main(String[] args)
            throws InterruptedException {

        int threads = 100;
        Thread[] workers = new Thread[threads];

        long start = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                double result = 0;

                for (int j = 0; j < 1_000_000; j++) {
                    result += Math.sqrt(j);
                }

                if (result < 0) {
                    System.out.println(result);
                }
            });

            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        System.out.println(
            "Time: " + (System.nanoTime() - start) / 1_000_000 + " ms"
        );
    }
}