package com.pengpeng.android.client.utils;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title AA
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/17 10:56
 */

class AA {

    static {
        System.out.print("-------static aaa---");
    }

    static int a = 1000;

    static {
        System.out.print(" a = " + a);
    }

    String b = "ss";

    AA() {
        System.out.print(" C  AA =  b" + b);
    }
}

public class B extends AA {
    static {
        System.out.print("-------static- bbb--");
    }

    static int a = 999999;

    static {
        System.out.print(" a = " + a);
    }

    String b = "ss";

    B() {
        System.out.print(" C BB = BB + " + b);
    }

    public static void main(String[] arg) {
        new B();
    }
}

