package com.yihao.algorithmexercise.sort;/* package codechef; // don't place package name! */

import java.util.Arrays;

/* Name of the class has to be "Main" only if the class is public. */
class HeapSort {


    public static void main(String[] args) throws Exception {
        // your code goes here
//        int[] array = new int[]{1, 3, 2, 4, 5};
//        Heap heap = new Heap(array);
//        heapSort(heap);
//        System.out.println(heap.toString());
        reverseBits(5);
    }

    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + (n & 1);
            n = n >>> 1;
        }
        return result;
    }

    public static void heapSort(Heap heap) {
        if (null == heap) {
            return;
        }
        int n = heap.getSize();
        while (n > 1) {
            buildMaxHeap(heap);
            heap.exchange(1, n);
            heap.setSize(--n);
        }
    }

    public static void buildMaxHeap(Heap heap) {
        if (null == heap) {
            return;
        }
        for (int i = heap.getSize() / 2; i >= 1; i--) {
            maxHeapify(heap, i);
        }
    }

    public static void maxHeapify(Heap heap, int n) {
        if (heap == null) {
            return;
        }
        if (n < 1 || n > heap.getSize()) {
            return;
        }

        int maxIndex = n;
        int maxValue = heap.get(n);
        try {
            int left = heap.leftChild(n);
            if (left > maxValue) {
                maxIndex = n * 2;
                maxValue = left;
            }

        } catch (IndexOutOfBoundsException exp) {
        }

        try {
            int right = heap.rightChild(n);
            if (right > maxValue) {
                maxIndex = n * 2 + 1;
            }
        } catch (IndexOutOfBoundsException exp) {
        }
        if (maxIndex != n) {
            heap.exchange(maxIndex, n);
            maxHeapify(heap, maxIndex);
        }
    }

    public static class Heap {
        private int[] numbers;
        private int size;
        private int length;

        public Heap(int[] numbers) {
            this.numbers = numbers;
            size = numbers.length;
            length = size;
        }

        public void setSize(int size) {
            if (size < 0 || size >= this.size) {
                return;
            }
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public int get(int index) throws IndexOutOfBoundsException {
            if (index < 1 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            return numbers[index - 1];
        }

        public int leftChild(int index) throws IndexOutOfBoundsException {
            return get(index * 2);
        }

        public int rightChild(int index) throws IndexOutOfBoundsException {
            return get(index * 2 + 1);
        }

        public void exchange(int a, int b) {
            if (a < 1 || a > size) {
                throw new IndexOutOfBoundsException();
            }

            if (b < 1 || b > size) {
                throw new IndexOutOfBoundsException();
            }

            int temp = numbers[a - 1];
            numbers[a - 1] = numbers[b - 1];
            numbers[b - 1] = temp;
        }

        @Override
        public String toString() {
            if (numbers.length == 0 || size == 0) {
                return "";
            }
            String result = String.valueOf(numbers[0]);
            for (int i = 1; i < length; i++) {
                result = result + "," + numbers[i];
            }
            return result;
        }
    }

}
