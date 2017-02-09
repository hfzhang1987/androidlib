package com.pengpeng.android.client.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseAPP
 * @Package com.pengpeng.android.client.base
 * @Description:
 * @date 2017/2/4 18:05
 */

public class BaseAPP extends Application {


    @Override
    public void onCreate() {
        LeakCanary.install(this);
        super.onCreate();
    }
}
