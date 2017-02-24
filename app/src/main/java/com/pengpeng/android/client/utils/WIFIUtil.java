package com.pengpeng.android.client.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title WIFIUtil
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/24 19:23
 */

public class WIFIUtil {
        /**
         * 判断是否连接WIFI
         * @param context  上下文
         * @return  boolean
         */
        public static boolean isWifiConnected(Context context) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI);
            if (wifiNetworkInfo.isConnected()) {
                return true;
            }
            return false;
        }
}
