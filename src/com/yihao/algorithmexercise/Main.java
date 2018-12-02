package com.yihao.algorithmexercise;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        SelectionTest.test();
//        Util.test();
//        PrintOut.printOut(567.123467);

//        PrintOne.test();
//        PermuteString.permute("aab");
        double  a =  0.1389008;
        System.out.println(a);
        a = a * 10;
        System.out.println(a - (int)a);
        a = a * 10;
        System.out.println(a- (int)a);

    }

    public static void testFloatPrcesion(){
        Float f = -0.25f;
        int i = Float.floatToIntBits(f);
        System.out.print(Integer.toBinaryString(i));

//        f = 33554432f;
//        System.out.println(String.format("%.3f", f));
//        f = 0.0f;
//        for (i = 0; i < 100000000; i++) {
//            f += 1;
//            int j = Float.floatToIntBits(f);
//            if (i > 16777200 && i <16777220 ) {
//
//                System.out.println(Integer.toBinaryString(j));
//            }
//        }
        System.out.println(String.format("%.3f", f));



    }
}
