package com.pengpeng.android.client.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
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

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);

}
