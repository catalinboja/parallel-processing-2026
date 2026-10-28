package ro.ase.ie.paralel;

import java.util.Arrays;


// Loop-carried dependency 
// Problem: Some loop iterations depend on results from earlier iterations.

public class LoopDependency {
    public static void main(String[] args)
            throws InterruptedException {

        int[] a = {1, 1, 1, 1, 1, 1};

        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 3; i++) {
                a[i] += a[i - 1];
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 3; i < 6; i++) {
                a[i] += a[i - 1];
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Arrays.toString(a));
    }
}