package com.pengpeng.android.client.api.server;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseServer
 * @Package com.pengpeng.android.client.api.server
 * @Description:
 * @date 2017/2/10 14:50
 */

public abstract class BaseServer {

    public static final int NO_MORE = 1005;
    public static final int PARSE = 1001; // Some error in parse JSON.

    public abstract String getDomain();

    public abstract Class getHodlerClass();

    public abstract int getOkCode();

}
