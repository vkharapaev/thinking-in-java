package concurrency.hw1;

import jdk.swing.interop.SwingInterOpUtils;

public class Task implements Runnable {

    public Task() {
        System.out.printf("%s start%n", Thread.currentThread().getName());
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s hello world %d %n", Thread.currentThread().getName(), i);
            Thread.yield();
        }
        System.out.printf("%s finish%n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Task()).start();
        }
    }
}
