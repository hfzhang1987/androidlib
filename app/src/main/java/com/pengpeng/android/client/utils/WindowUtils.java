package com.pengpeng.android.client.utils;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.pengpeng.android.client.base.Constants;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title WindowUtils
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/14 17:47
 */

public class WindowUtils {

    public static void setDisplayWidth_Height(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager =  (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Constants.displayWidth = displayMetrics.widthPixels;
        Constants.displayHeight = displayMetrics.heightPixels;
    }
}
