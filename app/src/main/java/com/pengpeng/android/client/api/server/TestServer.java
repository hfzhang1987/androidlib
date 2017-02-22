package com.pengpeng.android.client.api.server;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestServer
 * @Package com.pengpeng.android.client.api.server
 * @Description:
 * @date 2017/2/10 17:40
 */

public class TestServer extends BaseServer {

    private static TestServer INSTANCE = new TestServer();
    private final String DOMAIN = "http://172.16.49.37:8080/coamctech-assets-app/";
    private TestServer(){
    }
    public static TestServer getInstance(){
        return INSTANCE;
    }
    @Override
    public String getDomain() {
        return DOMAIN;
    }

    @Override
    public Class getHodlerClass() {
        return null;
    }

    @Override
    public int getOkCode() {
        return 0;
    }
}
