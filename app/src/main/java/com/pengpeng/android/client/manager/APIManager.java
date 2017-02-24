package com.pengpeng.android.client.manager;

import com.pengpeng.android.client.api.TestApi;
import com.pengpeng.android.client.api.interceptor.CacheInterceptor;
import com.pengpeng.android.client.api.interceptor.HeaderInterceptor;
import com.pengpeng.android.client.api.server.BaseServer;
import com.pengpeng.android.client.api.server.TestServer;
import com.pengpeng.android.client.base.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title APIManager
 * @Package com.pengpeng.android.client.manager
 * @Description:
 * @date 2017/2/10 11:30
 */

public class APIManager {
    private Retrofit mTestRetrofit;

    private static APIManager mAPIManager = new APIManager();

    private static APIManager getInstance() {
        return mAPIManager;
    }

    private APIManager() {
        mTestRetrofit = createRetrofit(TestServer.getInstance());
    }
    private Retrofit createRetrofit(BaseServer server) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.HTTP_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.HTTP_CONNECTTIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CacheInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server.getDomain())
                .build();
        return retrofit;
    }

    public static <T>T create(Class<T> api){
        if (TestApi.class == api){
            return APIManager.getInstance().mTestRetrofit.create(api);
        }
        return null;
    }

    public static BaseServer getServer(Class api) {
        if (TestApi.class == api) {
            return TestServer.getInstance();
        } else {
            return null;
        }
    }
}
