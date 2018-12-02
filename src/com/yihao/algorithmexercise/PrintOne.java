package com.yihao.algorithmexercise;

import com.sun.tools.javac.util.Assert;

/**
 * Created by yihao on 2018/8/26.
 * 习题1.5 打印自然数N中1的个数
 */
public class PrintOne {
    public static int getOneNumber(int N){
        if(N == 1){
            return 1;
        }
        if((N % 2) == 0){
            return getOneNumber(N/2);
        }
        else{
            return getOneNumber(N/2)+1;
        }
    }

    public static void test(){
        Assert.check(getOneNumber(1) == 1);
        Assert.check(getOneNumber(5) == 2);
        Assert.check(getOneNumber(8) == 1);
        Assert.check(getOneNumber(255) == 8);
    }


}
