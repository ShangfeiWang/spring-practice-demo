package com.wsf.sort;

/**
 * 快速排序
 *
 * @author wsf
 * @since 20230223
 * <p>
 * https://juejin.cn/post/6844903763044483086
 */
public class SortDemo4 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 8, 9, 2, 1, 89, 50, 32, 100};
        quickSort(arr, 0, 9);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ">");
        }
    }

    // 3  2  1
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int temp = qSort(arr, left, right);
            quickSort(arr, left, temp - 1);
            quickSort(arr, temp + 1, right);
        }
    }

    public static int qSort(int[] arr, int left, int right) {
        int temp = arr[left];// 定义基准数，默认为数组的第一个元素
        while (left < right) {

            while (left < right && arr[right] > temp) {
                right--;
            }

            if (left < right) {
                arr[left++] = arr[right];
            }

            while (left < right && arr[left] < temp) {
                left++;
            }

            if (left < right) {
                arr[right--] = arr[left];
            }

        }
        arr[left] = temp;
        return left;
    }

}
