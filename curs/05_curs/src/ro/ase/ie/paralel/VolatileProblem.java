package ro.ase.ie.paralel;

// Visibility vs. atomicity
// Problem: volatile makes updates visible, but does not make counter++ atomic.
// Fix: Use AtomicInteger.incrementAndGet(), a synchronized increment, or thread-local counters combined after join().

public class VolatileProblem {
    static volatile int counter = 0;

    public static void main(String[] args)
            throws InterruptedException {

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter++;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Expected: 200000");
        System.out.println("Actual: " + counter);
    }
}