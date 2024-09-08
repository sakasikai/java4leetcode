package Playground.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author maiqi
 * @title CompletableFuturePlay
 * @description TODO
 * @create 2024/9/1 15:17
 */
public class CompletableFuturePlay {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuturePlay r = new CompletableFuturePlay();
        r.testAsyncTask();
    }

    /**
     * @description: 父子线程通信
     * @author: maiqi
     * @update: 2024/9/8 11:04
     */
    public void testAsyncTask() {
        InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();
        THREAD_LOCAL.set("haha");

        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + " say: " + THREAD_LOCAL.get());
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).join();

        System.out.println(Thread.currentThread() + " hello!");
    }

    /**
     * @description: thenComposeAsync 和 thenCompose 的区别仍然是：
     * 用自定义线程还是原任务线程来 执行thenCompose回调函数，和要编排的任务taskA，taskB无关。
     * <p>
     * 联想：
     * thenApplyAsync + task
     * 和 thenCompose() + 异步调用(CompletableFuture) 的区别是，
     * 前者是简单的在原CompletableFuture中，异步执行了一个普通任务，并返回一个范型U的值
     * ==》Function<? super T,? extends U> fn
     * 后者是将一个 复杂的异步调用，和原异步调用串联并亚平，返回的是一个CompletableFuture<U>的值。
     * ==》Function<? super T, ? extends CompletionStage<U>>
     * @author: maiqi
     * @update: 2024/9/1 23:03
     */
    public void testAsyncTask5() {
        System.out.println("5. thenComposeAsync 和 thenCompose 的区别");
        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);


        Supplier<String> taskA = () -> {
            long time = 5_000;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskB = () -> {
            long time = 2000;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        String res = CompletableFuture.supplyAsync(taskA, myThreadPool)
                .thenApply(resA -> {
                    System.out.println("thread[taskA]:" + Thread.currentThread().getName());
                    return resA;
                })
                .thenComposeAsync(resA -> {
                    System.out.println("thread[thenComposeAsync_callback]: " + Thread.currentThread().getName());
                    return CompletableFuture.supplyAsync(taskB, myThreadPool);
                }, myThreadPool)
                .thenApply(resB -> {
                    System.out.println("thread[taskB]:" + Thread.currentThread().getName());
                    return "value";
                })
                .join();
        System.out.println("thread[taskB]:" + Thread.currentThread().getName() + " ==> " + res);
    }


    /**
     * @description: then*方法确实串联起来两个耗时任务
     * ==》等待原本的异步任务完成，继续在这个新开线程里作文章
     * <p>
     * thenApply()和thenApplyAsync()什么关系?
     * ==> 都在原线程执行完任务后，再执行耗时任务或回调。只不过，前者是原线程，后者是新开线程。
     * @author: maiqi
     * @update: 2024/9/1 16:16
     */
    public void testAsyncTask4() throws ExecutionException, InterruptedException {
        System.out.println("4. 编排异步任务：任务串行化");

        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);
        Supplier<String> taskA = () -> {
            long time = 10_000;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskB = () -> {
            long time = 2000;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskC = () -> {
            long time = 8500;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskD = () -> {
            long time = 3000;
            System.out.printf("task(%ds) started >>>%n", time / 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        // async taskA
        CompletableFuture.supplyAsync(taskA, myThreadPool)
                .thenAccept(res -> System.out.println(res + " <<< taskA done"));

        // async taskB(2s) sync => async taskC(8.5s)
        //                      => async taskD(3s)
        CompletableFuture.supplyAsync(taskB, myThreadPool)
                .thenAccept(res -> System.out.println(res + " <<< taskB done"))
                // taskB done
                .thenApplyAsync(res -> taskC.get(), myThreadPool)
                // async taskC 新开线程异步执行taskC; 因为taskC是函数，调用get才会执行 耗时任务
                .thenAccept(res -> System.out.println(res + " <<< taskC done"))

                .thenApply(res -> taskD.get()) // 没有新开线程，在上一个线程内继续完成taskD
                .thenAccept(res -> System.out.println(res + " <<< taskD done"));

        // main
        System.out.println(Thread.currentThread().getName() + "<<< done");
    }

    /**
     * @description: ref：https://blog.csdn.net/weixin_42373241/article/details/139300098
     * @author: maiqi
     * @update: 2024/9/1 15:53
     */
    public void testAsyncTask3() {


        System.out.println("get, join 中断和异常识别");

    }

    /**
     * @description: 结果
     * main thread：main
     * task 1s：pool-1-thread-2
     * task 5s：pool-1-thread-3
     * task 10s：pool-1-thread-1
     * <p>
     * 结论：
     * 1、runAsync开了新线程来异步执行任务。
     * 2、主线程、runAsync的线程，是并行的（互不相干）。
     * @author: maiqi
     * @update: 2024/9/1 15:33
     */
    public void testAsyncTask2() {
        System.out.println("异步执行耗时任务");

        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);
        Supplier<String> taskA = () -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskB = () -> {
            try {
                Thread.sleep(1000, TimeUnit.SECONDS.ordinal());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        Supplier<String> taskC = () -> {
            try {
                Thread.sleep(5000, TimeUnit.SECONDS.ordinal());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName();
        };

        System.out.println("1 测试异步行为");
        CompletableFuture.supplyAsync(taskA, myThreadPool)
                .thenAccept(res -> System.out.println("task 10s：" + res));

        CompletableFuture.supplyAsync(taskB, myThreadPool)
                .thenAccept(res -> System.out.println("task 1s：" + res));

        CompletableFuture.supplyAsync(taskC, myThreadPool)
                .thenAccept(res -> System.out.println("task 5s：" + res));

        System.out.println("main thread：" + Thread.currentThread().getName());
    }

    /**
     * @description: supplyAsync接口：
     * <p>
     * 默认使用 ForkJoinPool.commonPool 里的线程 ==》会公用线程
     * 设置线程池后，使用这个私有线程池的线程 ==》封装性好
     * @author: maiqi
     * @update: 2024/9/1 15:34
     */
    public void testAsyncTask1() {
        System.out.println("1 新开线程和线程池");

        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);
        Supplier<String> task = () -> Thread.currentThread().getName();

        // ForkJoinPool.commonPool-worker-1
        CompletableFuture.supplyAsync(task)
                .thenAccept(res -> System.out.println("默认 runAsync：" + res));

        // pool-1-thread-1
        CompletableFuture.supplyAsync(task, myThreadPool)
                .thenAccept(res -> System.out.println("设置线程池 runAsync：" + res));

    }


    /**
     * @description: ref：CompletableFuture极简教程，25分钟快速学会这个并发编程利器，超高效率！！
     * url：https://www.bilibili.com/video/BV1AN411S72m/?spm_id_from=333.337.search-card.all.click&vd_source=ab02f9767674de1156c22f5718a204e7
     * @author: maiqi
     * @update: 2024/8/31 23:04
     */
    public void note() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);

        // 耗时任务
        Runnable callbackA = () -> System.out.println(Thread.currentThread().getName());
        Runnable callbackA2 = () -> System.out.println(Thread.currentThread().getName());
        Supplier<String> callbackB = () -> Thread.currentThread().getName();
        Supplier<String> callbackB2 = () -> Thread.currentThread().getName();
        Consumer<String> callbackC = (var) -> System.out.println(var + " @ " + Thread.currentThread().getName());
        Function<String, String> callbackD = (var) -> var + " @ " + Thread.currentThread().getName();

        // 🍺1. 开线程，异步运行任务
        CompletableFuture<Void> futureTask = CompletableFuture.runAsync(callbackA, myThreadPool);
        CompletableFuture<String> futureTaskRet = CompletableFuture.supplyAsync(callbackB, myThreadPool);
        CompletableFuture<String> futureTaskRet2 = CompletableFuture.supplyAsync(callbackB2, myThreadPool);

        // 🍺2. 异步回调：任务运行完异步执行回调 ==》then*，不同API限定了任务的结构
        // 无入参(前一个任务无关)，无返回值
        // Runnable void run()
        futureTask.thenRun(callbackA2);

        // ✅有入参(需要前一个任务结果)，无返回值
        // Consumer void accept(T t)
        futureTaskRet.thenAccept(callbackC);


        // ✅有入参(需要前一个任务结果)，有返回值
        // Function<T, U> => R apply(T t)
        futureTaskRet.thenApply(callbackD);
        // e.g.
        futureTaskRet.thenApply(res -> res + "变换返回值")
                .thenApply(res -> "链式变换返回值 " + res)
                .thenAccept(res -> System.out.println("无返回值了：" + res));

        // 🍺3. 编排多个线程的执行顺序（任务）
        // ✅串行化(同步)执行两个线程任务
        // Function<T, CompletionStage<U>> => R apply(T t)
        futureTaskRet.thenCompose((res) -> futureTaskRet2);


        // ✅并行执行两个线程任务，整合两个任务的结果
        // futureTaskRet, futureTaskRet2 并行执行
        futureTaskRet.thenCombine(futureTaskRet2, (res1, res2) -> res1 + " && " + res2);


        // ✅异步执行两个线程任务，编排回调行为
        futureTaskRet.runAfterEither(futureTaskRet2, () -> System.out.println("任意一个执行完，触发回调"));
        futureTaskRet.runAfterBoth(futureTaskRet2, () -> System.out.println("全部执行完，触发回调"));

        // ✅并行执行，阻塞主线程，等待所有线程完成（编排线程和主线程执行顺序）
        CompletableFuture.allOf(futureTask, futureTaskRet, futureTaskRet2)
                .thenAccept(System.out::println)
                .join();

        // ✅并行执行，阻塞主线程，等待任意一个线程完成
        CompletableFuture.anyOf(futureTask, futureTaskRet, futureTaskRet2)
                .thenAccept(System.out::println)
                .join();

        // 🍺4. then*Async 会利用CompletableFuture的公共线程or设置的线程来「异步」执行
        // 待异步任务执行完，后置回调
        futureTask.thenRun(callbackA2);
        futureTaskRet.thenAcceptAsync(callbackC, myThreadPool);
        futureTaskRet.thenApplyAsync(callbackD, myThreadPool);

        // 编排两个异步任务的依赖关系（串行化两个异步任务）
        // 将一个CompletableFuture的结果作为另一个异步任务的输入
        futureTaskRet.thenComposeAsync((res) -> futureTaskRet2, myThreadPool);

        // 并行化两个异步任务，并对两个任务的结果，提供回调
        futureTaskRet.thenCombineAsync(futureTaskRet2, (res1, res2) -> res1 + " && " + res2, myThreadPool);

        // 两个任务只要跑完一个，触发回调（早）
        futureTaskRet.runAfterEitherAsync(futureTaskRet2, () -> System.out.println("任意一个执行完，触发回调"), myThreadPool);

        // 两个任务全部跑完了，才触发回调（晚）
        futureTaskRet.runAfterBothAsync(futureTaskRet2, () -> System.out.println("全部执行完，触发回调"), myThreadPool);

        // 下面接口是对 两个以上任务执行的
        // CompletableFuture.allOf
        // CompletableFuture.anyOf

        // 🍺5. 阻塞等待任务完成, 获取结果
        futureTask.get();
        futureTask.get(7, TimeUnit.DAYS);

        String taskRet = futureTaskRet.get();
        String nowRet = futureTaskRet.getNow("没完成就返回的默认值");

        // ✅暴力结束，任务没结束，就返回默认值
        // ==》超时结束线程
        if (futureTaskRet.complete("defaultValue"))
            System.out.println(futureTaskRet.get());

        futureTask.join();

        // 🍺6. 处理异常
        // ✅出现异常才触发回调
        Void unused = futureTask.exceptionally(err -> {
            err.printStackTrace();
            return null;
        }).get();

        String ret = futureTaskRet.exceptionally(err -> {
            err.printStackTrace();
            return "defaultValue";
        }).get();// 有返回值


        // ✅异步回调中处理异常，无返回值（类似thenAccept）
        futureTask.whenComplete((result, err) -> {
            if (err == null) {
                System.out.println("Result: " + result);
            } else {
                System.out.println("Exception: " + err.getMessage());
            }
        }).whenComplete((result, err) -> {
            if (err == null) {
                System.out.println("Result: " + result);
            } else {
                System.out.println("Exception: " + err.getMessage());
            }
        });

        // ✅异步回调中处理异常，有返回值（类似thenApply）
        futureTaskRet.handle((result, err) -> {
            if (err == null) {
                return result.toUpperCase();
            } else {
                return "Error: " + err.getMessage();
            }
        }).handle((result, err) -> {
            if (err == null) {
                return result.toUpperCase();
            } else {
                return "Error: " + err.getMessage();
            }
        }).thenAccept(System.out::println);

    }

}
