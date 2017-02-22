package com.pengpeng.android.client.mvp.base;

import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseCallBack
 * @Package com.pengpeng.android.client.mvp.base
 * @Description:
 * @date 2017/2/22 13:57
 */

public abstract class BaseCallBack implements Callback<ResponseBody>, CacheCallBack{
}
