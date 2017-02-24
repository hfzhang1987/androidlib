package com.pengpeng.android.client.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestApi
 * @Package com.pengpeng.android.client.api
 * @Description:
 * @date 2017/2/10 10:51
 */

public interface TestApi {

    /**
     * 如果请求为post实现，那么最好传递参数时使用@Field、@FieldMap和@FormUrlEncoded。
     * 因为@Query和或QueryMap都是将参数拼接在url后面的，而@Field或@FieldMap传递的参数时放在请求体的。
     * RxJava 在 GitHub 主页上的自我介绍是 "a library for composing asynchronous and event-based programs using observable sequences for the Java VM"
     * （一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库）。这就是 RxJava ，概括得非常精准。
     * http://gank.io/post/560e15be2dca930e00da1083
     */

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@FieldMap Map<String, String> params);
}
