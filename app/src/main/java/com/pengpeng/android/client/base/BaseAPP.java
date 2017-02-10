package com.pengpeng.android.client.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
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
        initLogger();
        super.onCreate();
    }

    private void initLogger(){
        //https://github.com/orhanobut/logger
        Logger.init()                           // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2); //default AndroidLogAdapter
    }
}
