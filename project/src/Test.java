import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task1 implements Runnable {
    @Override
    public void run() {
        synchronized (Test.lock) {
            while (true) {
                try {
                    System.out.println("Task1 tick");
                    Thread.sleep(1000);
                    Test.lock.wait();
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}

class Task2 implements Runnable {
    @Override
    public void run() {
        synchronized (Test.lock) {
            while (true) {
                try {
                    System.out.println("Task2 tick");
                    Thread.sleep(1000);
                    Test.lock.wait();
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}

class Task3 implements Runnable {
    @Override
    public void run() {
        synchronized (Test.lock) {
            while (true) {
                try {
                    System.out.println("Task3 tick");
                    Thread.sleep(1000);
                    Test.lock.notify();
                    Test.lock.wait();
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}

public class Test {

    public static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new Task1());
//        Thread.sleep(2000);
//        exec.execute(new Task2());
//        Thread.sleep(2000);
//        exec.execute(new Task3());
//        exec.shutdown();
        lock.notifyAll();
    }
}
