package com.yihao.algorithmexercise.datastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Created by yihao on 2018/12/2.
 * 队列是先进先出的数据结构
 */
public class Queue {
    private static final int SIZE = 5;
    private int[] array = new int[SIZE];  // 队列实际只能存储SIZE-1个元素
    private int head = 0;
    private int tail = 0;


    public void enqueue(int value) throws Exception {
        // queue is full, then throw overflow exception
        if ((tail - head + 1) % array.length == 0) {
            throw new Exception("queue overflow");
        }
        array[tail] = value;
        tail++;
        if (tail == array.length) {
            tail = 0;
        }
    }

    public int dequeue() throws Exception {
        // queue is empty, then throw underflow exception
        if (tail == head) {
            throw new Exception("queue underflow");
        }
        int result = array[head];
        head++;
        if (head == array.length) {
            head = 0;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (tail >= head) {
            builder.append(array[head]);
            for (int i = head + 1; i <= tail; i++) {
                builder.append(", ").append(array[i]);
            }
        } else {
            builder.append(array[head]);
            for (int i = head + 1; i < array.length; i++) {
                builder.append(", ").append(array[i]);
            }
            for (int i = 0; i <= tail; i++) {
                builder.append(", ").append(array[i]);
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("head", head);
        jsonObject.addProperty("tail", tail);
        jsonObject.addProperty("size", array.length);
        jsonObject.addProperty("content", builder.toString());
        return jsonObject.toString();
    }


    public static void main(String[] args) {
        Queue queue = new Queue();
        try{
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            System.out.println(queue.toString());
            queue.enqueue(5);
        }catch (Exception exp) {
            exp.printStackTrace();
        }

        try {
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.toString());
            System.out.println(queue.dequeue());
        }catch (Exception exp) {
            exp.printStackTrace();
        }

    }
}
