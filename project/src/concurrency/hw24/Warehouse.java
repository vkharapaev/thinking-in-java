package concurrency.hw24;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Warehouse {

    private final List<Item> queue;
    private static final int MAX_COUNT = 30;

    public Warehouse() {
        this.queue = new ArrayList<>();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Warehouse warehouse = new Warehouse();
        Producer producer = new Producer(warehouse);
        Consumer consumer = new Consumer(warehouse);
        exec.execute(producer);
        exec.execute(consumer);
        Thread.sleep(20000);
        exec.shutdownNow();
    }

    private static class Item {
        private static int count;
        private final int id = ++count;

        @Override
        public String toString() {
            return "Item{id=" + id + '}';
        }
    }

    private static class Producer implements Runnable {

        private final Warehouse wh;

        public Producer(Warehouse wh) {
            this.wh = wh;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Thread.sleep(100);
                    Item item = new Item();
                    synchronized (wh.queue) {
                        while (wh.queue.size() >= Warehouse.MAX_COUNT) {
                            wh.queue.wait();
                        }
                        wh.queue.add(item);
                        System.out.printf("Producer: produced %s, total = %d \n", item, wh.queue.size());
                        wh.queue.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Warehouse wh;

        private Consumer(Warehouse wh) {
            this.wh = wh;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    synchronized (wh.queue) {
                        while (wh.queue.size() == 0) {
                            wh.queue.wait();
                        }
                    }
                    Thread.sleep(2000);
                    synchronized (wh.queue) {
                        Item item = wh.queue.remove(0);
                        System.out.printf("Consumer: consumed %s, total = %d \n", item, wh.queue.size());
                        wh.queue.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
