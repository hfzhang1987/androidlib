package com.pengpeng.android.client.mvp.base.callback;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title CacheCallBack
 * @Package com.pengpeng.android.client.mvp.base
 * @Description:
 * @date 2017/2/22 11:53
 */

public interface CacheCallBack {

    void onLoadCache(long cacheTime,String cacheStr);
}
