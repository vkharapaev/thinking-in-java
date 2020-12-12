package concurrency.hw15;

abstract class Test {
    private static final int count = 5;

    void test(String name, Object lock) {
        try {
            synchronized (lock) {
                for (int i = 0; i < count; i++) {
                    System.out.print(name + " ");
                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException ignore) {
        }
    }

    abstract void m1();

    abstract void m2();

    abstract void m3();
}

class Same extends Test {
    @Override
    void m1() {
        test("11111", this);
    }

    @Override
    void m2() {
        test("22222", this);
    }

    @Override
    void m3() {
        test("33333", this);
    }
}

class Different extends Test {
    @Override
    void m1() {
        test("11111", new Object());
    }

    @Override
    void m2() {
        test("22222", new Object());
    }

    @Override
    void m3() {
        test("33333", new Object());
    }
}

public class HW15 {

    public static void main(String[] args) {
//        Same same = new Same();
//        new Thread(() -> same.m1()).start();
//        new Thread(() -> same.m2()).start();
//        new Thread(() -> same.m3()).start();

        Different different = new Different();
        new Thread(() -> different.m1()).start();
        new Thread(() -> different.m2()).start();
        new Thread(() -> different.m3()).start();
    }
}
