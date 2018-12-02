package com.yihao.algorithmexercise.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 2, 5, 3};
        quickSort(array, 0, 4);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(array, p, r);
        quickSort(array, p, q - 1);
        quickSort(array, q + 1, r);
    }

    public static int partition(int[] array, int p, int r) {
        int x = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                exchange(array, i, j);
            }
        }
        exchange(array, i + 1, r);
        System.out.println(i + 1);
        System.out.println(Arrays.toString(array));
        return i + 1;
    }

    public static void exchange(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}