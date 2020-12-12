package concurrency.hw16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

abstract class Test {
    private static final int count = 5;

    void test(String name, ReentrantLock lock) {
        lock.lock();
        try {
            for (int i = 0; i < count; i++) {
                System.out.print(name + " ");
                Thread.sleep(50);
            }
        } catch (InterruptedException ignore) {

        } finally {
            lock.unlock();
        }
    }

    abstract void m1();

    abstract void m2();

    abstract void m3();
}

class Same extends Test {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    void m1() {
        test("11111", lock);
    }

    @Override
    void m2() {
        test("22222", lock);
    }

    @Override
    void m3() {
        test("33333", lock);
    }
}

class Different extends Test {
    @Override
    void m1() {
        test("44444", new ReentrantLock());
    }

    @Override
    void m2() {
        test("55555", new ReentrantLock());
    }

    @Override
    void m3() {
        test("66666", new ReentrantLock());
    }
}

public class HW16 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        Same same = new Same();
        exec.execute(() -> same.m1());
        exec.execute(() -> same.m2());
        exec.execute(() -> same.m3());

        exec.shutdown();
        exec.awaitTermination(2, TimeUnit.DAYS);

        System.out.println();

        Different different = new Different();
        exec.execute(() -> different.m1());
        exec.execute(() -> different.m2());
        exec.execute(() -> different.m3());

        exec.shutdown();
    }
}
