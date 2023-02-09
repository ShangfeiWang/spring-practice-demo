package com.wsf.sort;

/**
 * 冒泡排序
 *
 * @author wsf
 * @since 20230223
 */
public class SortDemo1 {

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 4, 8, 9, 2, 1 };
        sort(arr);
    }

    public static void sort(int[] arr) {
        if (arr == null) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ">");
        }
    }

}
