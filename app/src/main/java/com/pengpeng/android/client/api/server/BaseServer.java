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

    public abstract String getDomain();

    public abstract Class getHodlerClass();

    public abstract int getOkCode();

}
