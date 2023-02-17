package com.wsf.sort;

/**
 * 插入排序
 *
 * @author wsf
 * @since 20230223
 */
public class SortDemo3 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 8, 9, 2, 1};
        sort(arr);
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int target = arr[j];
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ">");
        }
    }

}
