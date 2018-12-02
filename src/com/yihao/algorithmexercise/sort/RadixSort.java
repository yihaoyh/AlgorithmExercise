package com.yihao.algorithmexercise.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] a = new int[]{789, 123,121,456,521,900};
        radixSort(a, 3);
    }

    /**
     * 基数排序
     * 
     * @param A
     * @param d
     */
    public static void radixSort(int[] a, int d) {
        for (int i = 0; i < d; i++) {
            a = countingSort(a, i);
        }
        System.out.println("result " + Arrays.toString(a));
    }

    /**
     * 对最大值为9的数组，取某一位进行计数排序
     * 
     * @param a     待排序的数组
     * @param b     存放排序后的结果
     * @param digit 位数序号
     */
    public static int[] countingSort(int[] a, int digit) {
        if (null == a || digit<0) {
            return null;
        }
        int[] b = new int[a.length];
        int k = 9;
        int[] c = new int[k + 1];
        for (int i = 0; i < k; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < a.length; i++) {
            int d = (int) Math.pow(10, digit);
            int value = a[i] / d % 10;
            c[value]++;
        }

        for (int i = 1; i <= k; i++) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; i--) {
            int d = (int) Math.pow(10, digit);
            int value = a[i] / d % 10;
            b[c[value] - 1] = a[i];
            c[value]--;
        }
        return b;
    }
}