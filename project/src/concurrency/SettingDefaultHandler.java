package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingDefaultHandler {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread());
            throw new RuntimeException();
        });

        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        t.start();

//
//        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new ExceptionThread());
    }
}
