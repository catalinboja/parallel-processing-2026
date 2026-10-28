package ro.ase.ie.paralel;


// Thread starvation
// Problem: A thread can be repeatedly delayed because another thread keeps holding a resource.
// Fix: Move operations that do not need synchronization, such as sleep(), outside the critical section.

public class StarvationProblem {
    static final Object lock = new Object();

    public static void main(String[] args)
            throws InterruptedException {

        Thread slow = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 acquired lock");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread waiting = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
            }
        });

        slow.start();
        Thread.sleep(100);
        waiting.start();

        slow.join();
        waiting.join();
    }
}
