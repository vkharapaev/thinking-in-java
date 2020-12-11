package concurrency.hw6;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("elapsed time: %d sec %n", (System.currentTimeMillis() - startTime) / 1000);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new Task());
        }
        exec.shutdown();
    }
}
