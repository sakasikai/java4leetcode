package Playground.concurrent;

import Playground.utils.ArrPrint;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool {
    /**
     * 四种创建方法
     */

    static class myThread extends Thread{
        public void run() {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "==> 继承Thread，override run()方法，耦合方式");
            ArrPrint.defaultln();
        }
    }

    static class myRunnable implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + "==> 实现Runnable，解耦并发机制，摆脱继承限制");
            ArrPrint.defaultln();
        }
    }

    /**
     * @description: <p>
     * {@link Executors#newCachedThreadPool} </p><p>
     * {@link Executors#newFixedThreadPool} </p><p>
     * {@link Executors#newSingleThreadExecutor} </p><p>
     * {@link Executors#newScheduledThreadPool} </p><p>
     * </p>
     * @update: 2023/9/7 18:12
     */
    public void playExecutorsNew() {
        // TODO java prebuilt-in ThreadPool
        // SynchronousQueue<Runnable>
        ExecutorService es1 = Executors.newCachedThreadPool();
        // LinkedBlockingQueue<Runnable>
        ExecutorService es2 = Executors.newFixedThreadPool(3);
        // LinkedBlockingQueue<Runnable>
        ExecutorService es3 = Executors.newSingleThreadExecutor();
        // DelayedWorkQueue<Runnable>
        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(3);

        es4.schedule(()->{
            System.out.println("*msg delay 11s ==> "+ Thread.currentThread().getName());
            es4.shutdown();
        }, 11, TimeUnit.SECONDS);

        es4.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("msg repeat per 4s with initial delay 2 ==> "+ Thread.currentThread().getName());
            }
        }, 2, 4, TimeUnit.SECONDS);
    }

    public void playThread(){
        myThread myThread1 = new myThread();
        myThread1.start();

        System.out.println("hhaha");

        // 匿名内部类
        new Thread(() ->{
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "==> 继承Thread，override run()方法，耦合方式");
            ArrPrint.defaultln();
        }).start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "==> 继承Thread，override run()方法，耦合方式");
            ArrPrint.defaultln();
        });
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.playThread();

//        myThread1.start(); // java.lang.IllegalThreadStateException

        int num = -1;
        for(int i=0; i<num; i++){
            switch (i){
                case 0:
                    // TODO ExecutorService.submit(Callable<T>) ==> Future<T>
                    System.out.println("ExecutorService.submit & Callable<T> 实现有返回值的线程");
                    List<Future<String>> ret = new ArrayList<>();
                    List<String> res = new ArrayList<>();

                    List<Integer> data = Stream.of(1, 2, 3).collect(Collectors.toList());
                    ExecutorService es0 = Executors.newFixedThreadPool(2);

                    for(int j=0; j<10; j++){
                        Future<String> f = es0.submit(new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                List<String> collect = data.stream().map(Object::toString).collect(Collectors.toList());
                                Integer ret = data.stream().reduce((p, q) -> p*q).get();
                                return String.join("*", collect) + " = " + ret;
                            }
                        });

                        ret.add(f);
                        System.out.println("=-=" + f.get());
                        Thread.sleep(100L);
                        data.add(j+4); // change
                    }
                    es0.shutdown();

                    ret.forEach(x-> {
                        try {
                            res.add(x.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    });
                    res.forEach(System.out::println);

                    break;
                case 1:
                    // TODO ThreadPool.execute(Runnable r)
                    int tcnt = 1; // thread count
                    while(tcnt <= 10){
                        // TODO ThreadPoolExecutor with hooks
                        TaskExecutor exe = new ThreadPoolTaskExecutor();
                        ExecutorService es = new ThreadPoolExecutor(
                                3, 5,
                                1L, TimeUnit.MINUTES,
                                new ArrayBlockingQueue<>(4),
                                Executors.defaultThreadFactory(),
                                new ThreadPoolExecutor.AbortPolicy()){
                            @Override
                            protected void terminated()
                            {
                                System.out.println(Thread.currentThread().getName() + " 调度器已停止...");
                            }
                            @Override
                            protected void beforeExecute(Thread t,Runnable target)
                            {
                                System.out.println(Thread.currentThread().getName() + " 前钩执行...");
                                super.beforeExecute(t, target);
                            }
                            @Override
                            protected void afterExecute(Runnable target,Throwable t)
                            {
                                System.out.println(Thread.currentThread().getName() + " 后钩执行...");
                                super.afterExecute(target, t);
                            }
                        };

                        System.out.println(String.format("* concurrent cnt = %d:", tcnt));
                        for (int j = 0; j < tcnt; j++) {
                            es.execute(()->{
                                System.out.println(Thread.currentThread().getName()
                                        + "==> ThreadPoolExecutor");
                                try {
                                    Thread.sleep(30L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });

//                            es.execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                    System.out.println("test");
//                                }
//                            });
                        }

                        es.shutdown();
                        Thread.sleep(100L);
                        tcnt ++;
                        ArrPrint.defaultln();
                    }

                    break;
                case 2:
                    Thread thread = new Thread(new myRunnable());
                    thread.start();

                    Thread thread1 = new Thread(() -> {
                        System.out.println(Thread.currentThread().getName()
                                + "==> 实现Runnable，解耦并发机制，摆脱继承限制");
                        ArrPrint.defaultln();
                    });
                    thread1.start();
                    break;

                case 3:
                    myThread myThread = new myThread();
                    myThread.start();
                    break;
            }
            Thread.sleep(2000L);
        }
    }

    /**
     * analysis
     *
     * * concurrent cnt = 7:
     * pool-7-thread-1==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-2==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-3==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-1==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-2==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-3==> ThreadPoolExecutor & Callable<Class>
     * pool-7-thread-1==> ThreadPoolExecutor & Callable<Class>
     *
     * corePoolSize = 3, workQueue = 4，所以不会创建新线程，用这三个就够了
     *
     *
     * * concurrent cnt = 10:
     * Task playground.concurrent.ThreadPool$$Lambda$1/81628611@3941a79c
     * rejected from
     * java.util.concurrent.ThreadPoolExecutor@506e1b77
     * [Running, pool size = 5, active threads = 5, queued tasks = 4, completed tasks = 0
     * 第10个Thread被 ThreadPoolExecutor 拒绝了，因为最大处理能力是9
     */
}
