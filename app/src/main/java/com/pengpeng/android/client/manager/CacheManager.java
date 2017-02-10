package com.pengpeng.android.client.manager;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title CacheManager
 * @Package com.pengpeng.android.client.manager
 * @Description:
 * @date 2017/2/10 14:02
 */

public class CacheManager {
    private static CacheManager mCacheManager = new CacheManager();
    public static CacheManager getInstance() {
        return mCacheManager;
    }

    public void writeCache(Request request, Response response) {

    }

    public String readCache(Request request, Response response) {
        return null;
    }
}
