package lc_contest;

import java.util.Arrays;
import java.util.Scanner;

public class t3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        /*
        x >= ai, x = ai
        x < ai, x = ai && gain = ai - x

        1 2 2
        2 1 2 => 2 + 1
        */

        Arrays.sort(arr);
        int gain = 0, x = 0;
        boolean flag = true;
        for (int i = 0, j = n - 1; i <= j; ) {
            if (i == j) {
                if (x < arr[i]) gain += arr[i] - x;
                break;
            }
            if (flag) {
                gain += arr[j] - x;
                x = arr[j];
                j--;
                flag = false;
            } else {
                x = arr[i++];
                flag = true;
            }
        }

        System.out.println(gain);
    }
}