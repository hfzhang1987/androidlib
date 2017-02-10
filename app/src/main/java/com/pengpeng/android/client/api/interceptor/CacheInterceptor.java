package com.pengpeng.android.client.api.interceptor;

import com.pengpeng.android.client.manager.CacheManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        CacheManager.getInstance().writeCache(request,response);
        return response;
    }

}
