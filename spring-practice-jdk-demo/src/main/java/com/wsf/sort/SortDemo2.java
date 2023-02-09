package com.wsf.sort;

/**
 * 选择排序
 *
 * @author wsf
 * @since 20230223
 */
public class SortDemo2 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 8, 9, 2, 1};
        sort(arr);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ">");
        }
    }

}
