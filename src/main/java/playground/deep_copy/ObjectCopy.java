package playground.deep_copy;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maiqi
 * @Title: ObjectCopy
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 10:17
 */
public class ObjectCopy {

    private static int[] arrayOriginal = new int[1024 * 1024 * 10];
    private static int[] arraySrc = new int[1024 * 1024 * 10];
    private static int[] arrayDest = new int[1024 * 1024 * 10];
    private static ReentrantLock lock = new ReentrantLock();

    private static void modify() {
        for (int i = 0; i < arraySrc.length; i++) {
            arraySrc[i] = i + 1;
        }
    }

    private static void copy() {
        System.arraycopy(arraySrc, 0, arrayDest, 0, arraySrc.length);
    }

    private static void init() {
        for (int i = 0; i < arraySrc.length; i++) {
            arrayOriginal[i] = i;
            arraySrc[i] = i;
            arrayDest[i] = 0;
        }
    }

    private static void doThreadSafeCheck() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("run count: " + (i + 1));
            init();
            Condition condition = lock.newCondition();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    condition.signalAll();
                    lock.unlock();
                    copy();
                }
            }).start();


            lock.lock();
            // 这里使用 Condition 来保证拷贝线程先已经运行了.
            condition.await();
            lock.unlock();

            Thread.sleep(2); // 休眠2毫秒, 确保拷贝操作已经执行了, 才执行修改操作.
            modify();

            // 如果 System.arraycopy 是线程安全的, 那么先执行拷贝操作, 再执行修改操作时, 不会影响复制结果, 因此 arrayOriginal 必然等于 arrayDist;
            if (!Arrays.equals(arrayOriginal, arrayDest)) {
                throw new RuntimeException("System.arraycopy is not thread safe");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //doThreadSafeCheck();
        executeTime();
    }

    private static void executeTime() {
        String[] srcArray = new String[1000000];
        String[] forArray = new String[srcArray.length];
        String[] arrayCopyArray = new String[srcArray.length];

        //初始化数组
        for (int i = 0; i < srcArray.length; i++) {
            srcArray[i] = String.valueOf(i);
        }

        long forStartTime = System.currentTimeMillis();
        for (int index = 0; index < srcArray.length; index++) {
            forArray[index] = srcArray[index];
        }
        long forEndTime = System.currentTimeMillis();
        System.out.println("for方式复制数组：" + (forEndTime - forStartTime));

        long arrayCopyStartTime = System.currentTimeMillis();
        System.arraycopy(srcArray, 0, arrayCopyArray, 0, srcArray.length);
        long arrayCopyEndTime = System.currentTimeMillis();
        System.out.println("System.arraycopy复制数组：" + (arrayCopyEndTime - arrayCopyStartTime));
    }
}