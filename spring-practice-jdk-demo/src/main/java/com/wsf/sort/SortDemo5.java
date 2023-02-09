package com.wsf.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author wsf
 * @since 20230223
 * <p>
 * https://juejin.cn/post/6844903763044483086
 */
public class SortDemo5 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 8, 9, 2, 1, 10, 7, 6};

        shellSort(arr);
    }


    public static void shellSort(int[] arr) {
        System.out.println(Arrays.toString(arr));

        int length = arr.length;
        int k = length / 2;

        // 增量最小值为1，既最后一次对整个数组做直接排序
        while (k > 0) {
            for (int i = k; i < length; i++) {
                int j = i;
                int target = arr[i];
                while (j >= k && target < arr[j - k]) {
                    arr[j] = arr[j - k];
                    j = j - k;
                }
                arr[j] = target;
            }
            System.out.println("增量为" + k + "排序之后：" + Arrays.toString(arr));
            k /= 2;
        }
    }

}
