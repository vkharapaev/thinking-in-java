import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Test {

    static class Task implements Runnable {

        private final CyclicBarrier barrier;
        private final String taskName;

        public Task(CyclicBarrier barrier, String taskName) {
            this.barrier = barrier;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this + " Prepare ");
                    Thread.sleep(1000);
                    barrier.await();
                    System.out.println(this + " End ");
                }
            } catch (Exception e) {
                System.out.println(this + " interrupted " + barrier.isBroken());
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "taskName='" + taskName + '\'' +
                    '}' + " " + Test.time() + ":";
        }
    }

    private synchronized static String time() {
        return System.nanoTime() + " " + Thread.currentThread();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {

            System.out.println("Finish action " + Test.time() + ": Prepare");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
            }
            System.out.println("Finish action " + Test.time() + ": End");

        });

//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new Task(barrier, "Task1"));
//        Thread.sleep(1000);
//        exec.execute(new Task(barrier, "Task2"));
//        Thread.sleep(2000);
//        exec.shutdownNow();

//        Thread task1 = new Thread(new Task(barrier, "Task1"));
//        Thread task2 = new Thread(new Task(barrier, "Task2"));
//        task1.start();
//        Thread.sleep(1000);
//        task2.start();
//        Thread.sleep(2000);
//        task1.interrupt();
//        task2.interrupt();

        List<? super Integer> foo3 = new ArrayList<>();         // Integer is a "superclass" of Integer (in this context)
        List<? super Integer> foo4 = new ArrayList<Number>();   // Number is a superclass of Integer
        List<? super Integer> foo5 = new ArrayList<Object>();   // Object is a superclass of Integer

        List<Object> tmp = new ArrayList<>();
        tmp.add("test");

        List<? super Number> foo6 = tmp;

        foo6.add(new Integer(10));
    }

//    public static List<? super Object> test(List<? super Integer> list) {
//
//    }

}
