package com.pengpeng.android.client.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.utils.WindowUtils;
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

    private static final String TAG = "Pengpeng";

    public static BaseAPP mBaseAPP;


    @Override
    public void onCreate() {
        mBaseAPP = this;
        LeakCanary.install(this);
        initLogger();
        WindowUtils.setDisplayWidth_Height(this);
        super.onCreate();
    }

    private void initLogger(){
        //https://github.com/orhanobut/logger
        Logger.init(TAG)                        // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);               // 设置调用堆栈的函数偏移值，默认是 0
    }
}
