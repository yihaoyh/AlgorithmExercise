package com.yihao.algorithmexercise;


import java.util.*;

/**
 * Created by yihao on 2018/8/24.
 */
public class Util {
    public static Integer[] generateRandomList(int size) {
        if (size <= 0) {
            return null;
        }
        List<Integer> result = new ArrayList<Integer>();
        int first = new Random().nextInt()%100;
        for(int i =0; i<size; i++){
            result.add(first + i);
        }

        Collections.shuffle(result);
        System.out.println("generateRandomList is " + Arrays.toString(result.toArray(new Integer[]{})));
        return result.toArray(new Integer[]{});
    }


    public static void test(){
        System.out.println("generateRandomList is " + Arrays.toString(generateRandomList(20)));
    }
}
