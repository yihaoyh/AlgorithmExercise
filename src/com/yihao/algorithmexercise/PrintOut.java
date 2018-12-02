package com.yihao.algorithmexercise;

import sun.tools.tree.DoubleExpression;

/**
 * Created by yihao on 2018/8/25.
 * 习题 1.3
 * 使用printDigit打印double数值
 *
 */
public class PrintOut {
    public static void printOut(double value) {
        value = Math.abs(value);
        if(value > 10){
            printOut(value / 10);
        }
        printDigit((int)value%10);
        if((value - (int)value) > Double.MIN_VALUE){
            printOut((value - (int)value)*10);
        }
    }

    public static void printDigit(int n) {
        if (0 <= n && n <= 9) {
            System.out.print(n);
        }
    }
}
