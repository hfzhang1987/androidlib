package com.pengpeng.android.client.mvp.presenter;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.api.TestApi;
import com.pengpeng.android.client.api.server.BaseServer;
import com.pengpeng.android.client.manager.APIManager;
import com.pengpeng.android.client.mvp.base.callback.DefaultCallback;
import com.pengpeng.android.client.mvp.model.TestBean;
import com.pengpeng.android.client.mvp.view.ILoadView;
import com.pengpeng.android.client.utils.CollectionsUtil;
import com.pengpeng.android.client.utils.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestPresenter
 * @Package com.pengpeng.android.client.mvp.presenter
 * @Description:
 * @date 2017/2/22 11:15
 */

public class TestPresenter extends LoadPresenter<TestBean> {
    /**
     * 1.通过接口创建API实例
     * 2.获取网络请求实例对象
     */
    private TestApi mTestApi;
    private BaseServer mBaseServer;

    @Override
    protected void initAPI() {
        mTestApi = APIManager.create(TestApi.class);
        mBaseServer = APIManager.getServer(TestApi.class);
    }

    public void login(ILoadView<TestBean> loadView) {
        hashMap = new HashMap<>();
        hashMap.put("loginName", "13900001235");
        hashMap.put("password", "12346789");
        hashMap = setRequestParams(hashMap);
        Call<ResponseBody> call = mTestApi.login(hashMap);
        loadData(false, call, loadView);
    }

    @Override
    protected Callback createCallBack(Call call, ILoadView<TestBean> loadView) {
        return new DefaultCallback(call,loadView,mBaseServer) {
            @Override
            protected List parseResult(String result) {
                Logger.e("createCallback result:  " + result);
                if (TextUtils.isEmpty(result)) {
                    return new ArrayList<TestBean>();
                } else
                    return CollectionsUtil.asList(GsonUtil.fromJson(result, TestBean.class));
            }
        };
    }
}
