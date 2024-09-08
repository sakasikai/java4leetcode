package core.basic.sort;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title heapSort
 * @description TODO
 * @create 2024/9/8 11:08
 */
public class HeapSort {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(0, 7, 10, 13, 15, 4, 20, 19, 8),
                targetHeap = Arrays.asList(0, 20, 15, 19, 10, 4, 13, 7, 8),
                targetSort = Arrays.asList(0, 4, 7, 8, 10, 13, 15, 19, 20);
        int scale = arr.size() - 1;
        HeapSort r = new HeapSort();

        r.heapInit(arr, scale);
        int bias = IntStream.range(1, scale).map(i -> Math.abs(arr.get(i) - targetHeap.get(i))).sum();
        Assert.isTrue(bias == 0, "err " + arr);

        r.heapSort(arr, scale);
        bias = IntStream.range(1, scale).map(i -> Math.abs(arr.get(i) - targetSort.get(i))).sum();
        Assert.isTrue(bias == 0, "err " + arr);

        System.out.println("test pass!");
    }

    /**
     * @param arr  模拟二叉树数组，1..n
     * @param root n确定后的某个节点
     * @param n    数组下标从1开始的 堆的元素规模
     * @description: 大根堆
     * @author: maiqi
     * @update: 2024/9/8 11:32
     */
    private void heapify(List<Integer> arr, int root, int n) {
        int i = root, j = 2 * i;
        int copy = arr.get(i);

        while (j <= n) {
            if (j < n && arr.get(j) < arr.get(j + 1)) {
                j++; // 兄弟间选大的，或者移动到同行的其他兄弟
            }

            if (copy < arr.get(j)) {
                arr.set(i, arr.get(j));
                i = j;
                j = 2 * i;
            } else {
                j = n + 1; // 什么都不做
            }
        }
        arr.set(i, copy);
    }

    public void heapInit(List<Integer> arr, int n) {
        for (int i = arr.size() / 2; i >= 1; i--) {
            heapify(arr, i, n);
        }
    }

    public void heapSort(List<Integer> arr, int n) {
        heapInit(arr, n);
        for (int i = n; i >= 2; i--) {
            // 最大 和 最后一个元素 交换
            Collections.swap(arr, 1, i);
            // 将最后一个最大元素孤立，将前i-1元素堆化，重复n-2次，实现排序
            heapify(arr, 1, i - 1);
        }
    }
}
