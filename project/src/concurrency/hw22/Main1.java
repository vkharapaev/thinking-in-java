package concurrency.hw22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class Task1 implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(500);
                Main1.flag = true;
                System.out.println("Task1 tick");
            }
        } catch (Exception e) {
        }
    }
}

class Task2 implements Runnable {

    private AtomicInteger count;

    public Task2() {
        count = new AtomicInteger(0);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (Main1.flag) {
                Main1.flag = false;
                System.out.println("Task2 tick");
            }
            count.incrementAndGet();
        }
    }

    public int getCount() {
        return count.get();
    }

}

public class Main1 {
    public static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Task1());
        Task2 task2 = new Task2();
        exec.execute(task2);
        Thread.sleep(5000);
        exec.shutdownNow();
        System.out.println("active waiting count " + task2.getCount());
    }
}
