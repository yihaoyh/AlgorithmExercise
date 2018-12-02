package com.yihao.algorithmexercise;

import java.util.Map;

/**
 * Created by yihao on 2018/10/20.
 */
public class PrintPowerOf2 {
    private static String[] results = new String[513];
    private static int max = 512;
    private static final String INITIAL = "13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096";

    static {
        results[0] = INITIAL;
    }

    public static String getResult(int n) {
        String initial = INITIAL;
        if (n < 512 || n > 1024) {
            return "INVALID";
        }
        if (n <= max) {
            return results[n - 512];
        } else {
            if (max != 0) {
                initial = results[max - 512];
            }
            for (int j = 1; j <= n - max; j++) {
                StringBuilder result = new StringBuilder(initial.length() + 1);
                int flag = 0;
                for (int i = initial.length() - 1; i >= 0; i--) {
                    int base = initial.charAt(i) - '0';
                    int curr = base * 2 % 10 + flag;
                    flag = base * 2 / 10;
                    result.insert(0, (char)(curr + '0'));
                }
                if (flag == 1) {
                    result.insert(0, "1");
                }
                initial = result.toString();
                results[j] = initial;
            }
            max = n;
            return results[max - 512];
        }
    }

    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        System.out.println(PrintPowerOf2.getResult(512));
        System.out.println(PrintPowerOf2.getResult(1024));
        System.out.println(PrintPowerOf2.getResult(1025));
        System.out.println(System.currentTimeMillis());

    }
}
