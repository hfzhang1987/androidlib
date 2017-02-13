package com.pengpeng.android.client;

import junit.framework.Test;

import java.lang.reflect.Field;

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
//        A a = new A();
//        a.test1(b);
//        System.out.print(b+"\n");
//        b ="22333222";
//        System.out.print(b);

        int b =2;
        b = test1(b);
        System.out.print(b);
//        int a =1;
    }
//java无法改变外部变量
    public static int test1(int b){
        A test = new A();
        Field field = null;
        try {
            field =A.class.getField("b");
            field.setAccessible(true);
            try {
                field.setInt(test,2000);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("b = " + b);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

return b;

//                b =200;
//        System.out.print(b);
//        System.exit(0);
//        return b;
    }

    public String test1(String b){
        b ="dd";
        return b;
    }
}
