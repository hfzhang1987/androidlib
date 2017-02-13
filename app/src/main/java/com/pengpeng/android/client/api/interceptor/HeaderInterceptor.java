package com.pengpeng.android.client.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 若要求Retrofit2的接口中每个都要增加上headers，又不想做重复的事情，
 * 可以使用这种方法来为每个request请求都设置上相同的请求头header。
 * 修改请求头request headers主要是通过拦截器来实现，如果还有增加其他的header，可以再进行增加。
 */
public class HeaderInterceptor implements Interceptor {
    /**
     * 请求拦截器，修改请求header
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "text/html; charset=UTF-8")
                //                    .addHeader("Accept-Encoding", "*")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Access-Control-Allow-Origin", "*")
                .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                .addHeader("Vary", "Accept-Encoding")
                //                    .addHeader("Cookie", "add cookies here")
                .build();
        return chain.proceed(request);
    }
}
