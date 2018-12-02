package com.yihao.algorithmexercise;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yihao on 2018/8/26.
 * 习题1.6 打印字符串的所有排列
 *
 */
public class PermuteString {
    public static void permute(String str){
        permute(str.toCharArray(), 0, str.length()-1);
    }

    private static void permute(char[] str, int low, int high){
        if(low == high){
            System.out.println(str);
        }
        else {
            for(int i=low; i<=high; i++){
                char[]newStr = Arrays.copyOf(str, str.length);
                swap(newStr, low, i);
                permute(newStr, low + 1, high);
            }
        }
    }

    private static void swap(char[] str, int i, int j){
        if(i <0 || i >= str.length){
            return;
        }

        if(j <0 || j >= str.length){
            return;
        }

        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }


}
