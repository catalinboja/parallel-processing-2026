package ro.ase.ie.paralel;

// Livelock
// Problem: Two threads are active but repeatedly change their behavior in response to each other without completing useful work.
// Fix: Introduce a rule that determines who proceeds, such as giving one participant priority.

public class LivelockProblem {
    static volatile boolean aliceWantsToPass = true;
    static volatile boolean bobWantsToPass = true;

    public static void main(String[] args)
            throws InterruptedException {

        Thread alice = new Thread(() -> {
            while (aliceWantsToPass && bobWantsToPass) {
                System.out.println("Alice: You go first!");
                Thread.yield();
            }
        });

        Thread bob = new Thread(() -> {
            while (aliceWantsToPass && bobWantsToPass) {
                System.out.println("Bob: No, you go first!");
                Thread.yield();
            }
        });

        alice.start();
        bob.start();

        Thread.sleep(100);

        aliceWantsToPass = false;

        alice.join();
        bob.join();

        System.out.println("Finished");
    }
}