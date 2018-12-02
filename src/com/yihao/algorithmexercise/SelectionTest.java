package com.yihao.algorithmexercise;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yihao on 2018/8/23.
 *
 * 输入一组数，求第k个最大值
 * 习题 1.1
 */
public class SelectionTest<T> {
    T getKMax(T[] array, int k){
        if(array == null || k<0||k>=array.length){
            return null;
        }
        Arrays.sort(array, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1 instanceof Integer){
                    return Integer.compare((Integer) o1, (Integer)o2);
                }
                else if(o1 instanceof Float){
                    return Float.compare((Float) o1, (Float)o2);
                }
                else if(o1 instanceof Double){
                    return Double.compare((Double) o1, (Double)o2);
                }
                return 0;
            }
        });
        return array[array.length-1-k];
    }

    public static void test(){
        SelectionTest<Integer> test = new SelectionTest<Integer>();
        Integer result = test.getKMax(Util.generateRandomList(5), 0);
        System.out.println("k max value is " + result);
    }
}
