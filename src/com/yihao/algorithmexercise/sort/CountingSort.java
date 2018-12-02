package com.yihao.algorithmexercise.sort;

import java.util.Arrays;

public class CountingSort
{
    public static void main (String[] args) {
        int[] a = {2,5,3,0,2,3,0,3,};
        int[] b = new int[a.length];
        countingSort(a, b, 5);
        System.out.println(Arrays.toString(b));
    }

    public static void countingSort(int[] a, int [] b, int k) {
        int[] c = new int[k + 1];
        for (int i = 0; i < k; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < a.length; i++) {
            c[a[i]]++;
        }

        for (int i = 1; i <= k; i++) {
            c[i] = c[i] + c[i - 1];
        }
        System.out.println("c[] is" + Arrays.toString(c));
        for (int i = a.length - 1 ; i >= 0; i--) {
            b[c[a[i]] - 1] = a[i];
            c[a[i]] = c[a[i]] - 1;
            System.out.println("b[] is" + Arrays.toString(b));
            System.out.println("c[] is" + Arrays.toString(c));
        }
    }
}