package com.pengpeng.android.client;

import com.pengpeng.android.client.mvp.model.TestBean;

import java.io.IOException;
import java.lang.reflect.Field;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title A
 * @Package com.pengpeng.android.client
 * @Description:
 * @date 2017/2/8 16:41
 */

public class A {
    private static String b = "ccsd";
    private TestBean t =new TestBean();

    public static void main(String[] args) {
//        A a = new A();
//        TestBean t =new TestBean();
//        a.test2(t);
//        a.test2(t);
//        System.out.print(t.getId());
//        A a = new A();
//        a.test1(b);
//        System.out.print(b+"\n");
//        b ="22333222";
//        System.out.print(b);
//
//        int b = 21;
//        boolean b = true;
//        test1(b);
//        System.out.print(b);
        testBS64();

    }


    public static void testBS64(){
        BASE64Encoder encode = new BASE64Encoder();

        String base64 = encode.encode(" 五笔字型电子计算机".getBytes());

        System.out.println(base64);


        BASE64Decoder decode = new BASE64Decoder();

        byte [] b = new byte[0];
        try {
            b = decode.decodeBuffer(base64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( new String(b));
    }

    //java无法改变外部变量
    public static int test1(int b) {
        b=1000;
        return b;

//        b =200;
//        System.out.print(b);
//        System.exit(0);
//        return b;
    }
    //java无法改变外部变量
    public static void test1(boolean b) {
        b=false;
//        b =200;
//        System.out.print(b);
//        System.exit(0);
//        return b;
    }

    public String test1(String b) {
        b = "dd";
        return b;
    }
    public void test2(TestBean t) {
        t.setId("33333333333333333");
    }
}


