package ro.ase.ie.paralel;

// False sharing
// Problem: Two threads update different variables, but the variables may share a cache line.
public class FalseSharing {
	
    static class Counters {
        volatile long a = 0;
        volatile long b = 0;
    }

    static Counters counters = new Counters();

    public static void main(String[] args)
            throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20_000_000; i++) {
                counters.a++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20_000_000; i++) {
                counters.b++;
            }
        });

        long start = System.nanoTime();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(
            "Time: " + (System.nanoTime() - start) / 1_000_000 + " ms"
        );
    }
}
