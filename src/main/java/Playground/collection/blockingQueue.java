package Playground.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class blockingQueue {
    static class Producer implements Runnable {
        private final BlockingQueue queue;
        Producer(BlockingQueue q) { queue = q; }

        public void run() {
            try {
                int pId = 1;
                while (true) { // 一直生产
                    Thread.sleep(1000L);
                    queue.put(produce(pId ++));
                }
            } catch (InterruptedException ex) {
                // ... handle ...
            }
        }

        public Object produce(int x) {
            //...
            String ret = "==> product[" + x + "] " + Thread.currentThread().getName();
            System.out.println(ret);
            System.out.println();
            return Integer.valueOf(x);
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;
        Consumer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                    Thread.sleep(3000L);
                }
            } catch (InterruptedException ex) {
                // ... handle ...
            }
        }
        void consume(Object x) {
            System.out.println("<== consumed ["+x+"] " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>(60);

        // BlockingQueue q = new SomeQueueImplementation();
        BlockingQueue q = new ArrayBlockingQueue(10);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
    }
}

