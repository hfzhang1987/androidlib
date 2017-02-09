package com.pengpeng.android.client;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title A
 * @Package com.pengpeng.android.client
 * @Description:
 * @date 2017/2/8 16:41
 */

public class A {
    private static String b = "ccd";
    public static void main(String[] args){
        A a = new A();
        a.test1(b);
        System.out.print(b+"\n");
        b ="22222";
        System.out.print(b);
    }

    public String test1(String b){
        b ="dd";
        return b;
    }
}
