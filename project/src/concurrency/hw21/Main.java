package concurrency.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
                System.out.println("Task1 message");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task2 implements Runnable {

    private Task1 task1;

    public Task2(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        synchronized (task1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task1.notify();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Task1 task1 = new Task1();
        exec.execute(task1);
        Thread.sleep(1000);
        exec.execute(new Task2(task1));
        exec.shutdown();
    }
}
