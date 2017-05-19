package org.com.cn.xu;

public class TestCatch {
    public static void main(String[] args) {
        try {
            if (test()) {
                System.out.println("true!");
            } else {
                System.out.println("false!");
            }
        } catch (Exception e) {
            System.out.println("out-catch!!!");
            System.out.println(e);
            return;
        }
        System.out.println("programe is go on!");
    }

    static boolean test() throws Exception {
        try {
            String str = null;
//            System.out.println(str.length());
            return str.length() > 0;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!!!");
            return false;
        }
    }
}