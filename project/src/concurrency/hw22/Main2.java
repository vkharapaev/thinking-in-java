package concurrency.hw22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class Main2 {

    public static volatile boolean flag = false;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Task1());
        Task2 task2 = new Task2();
        exec.execute(task2);
        Thread.sleep(5000);
        exec.shutdownNow();
        System.out.println("active waiting count " + task2.getCount());
    }

    static class Task1 implements Runnable {

        @Override
        public void run() {
            synchronized (LOCK) {
                try {
                    while (!Thread.interrupted()) {
                        Thread.sleep(500);
                        Main2.flag = true;
                        LOCK.notify();
                        LOCK.wait();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    static class Task2 implements Runnable {

        private AtomicInteger count;

        public Task2() {
            count = new AtomicInteger(0);
        }

        @Override
        public void run() {
            synchronized (LOCK) {
                try {
                    while (!Thread.interrupted()) {
                        if (Main2.flag) {
                            Main2.flag = false;
                            System.out.println("Task2 flag was set to false");
                        }
                        count.incrementAndGet();
                        LOCK.notify();
                        LOCK.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public int getCount() {
            return count.get();
        }

    }
}
