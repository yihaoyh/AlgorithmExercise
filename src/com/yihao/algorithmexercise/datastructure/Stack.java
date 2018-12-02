package com.yihao.algorithmexercise.datastructure;

/**
 * Created by yihao on 2018/12/1.
 * 一个先进后出的数据结构，通过{@link #pop()}来取数据，通过{@link #push(int)}来添加数据
 */
public class Stack {
    private int size = 10;

    private int[] array = new int[size];

    private int top = 0;

    public void push(int value) {
        if (top < size) {
            array[top] = value;
            top++;
        }
    }

    public int pop() {
        if (top > 0) {
            top--;
            return array[top];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.valueOf(array[top - 1]));
        for (int i = top - 2; i >= 0; i--) {
            result.append(", ").append(String.valueOf(array[i]));
        }
        return result.toString();
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.toString()); // 5, 4, 3, 2, 1
        System.out.println(stack.pop()); // 5
        System.out.println(stack.pop()); // 4
        System.out.println(stack.toString()); // 3, 2, 1
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // throw exception

    }

}
