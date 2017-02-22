package com.pengpeng.android.client.mvp.presenter;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.api.TestApi;
import com.pengpeng.android.client.api.server.BaseServer;
import com.pengpeng.android.client.manager.APIManager;
import com.pengpeng.android.client.mvp.base.DefaultCallback;
import com.pengpeng.android.client.mvp.model.TestBean;
import com.pengpeng.android.client.mvp.view.ILoadView;
import com.pengpeng.android.client.utils.CollectionsUtil;
import com.pengpeng.android.client.utils.GsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     *
     */
    private TestApi mTestApi;
    private BaseServer mBaseServer;
    @Override
    protected void initAPI() {
        mTestApi = APIManager.create(TestApi.class);
        mBaseServer = APIManager.getServer(TestApi.class);
    }

    @Override
    protected Callback createCallBack(Call call, ILoadView<TestBean> loadView) {
        return new DefaultCallback() {
            @Override
            protected List parseResult(String result) {
                Logger.e("createCallback result:  "+result);
                if(TextUtils.isEmpty(result)){
                    return new ArrayList<TestBean>();
                }else
                    return CollectionsUtil.asList(GsonUtil.fromJson(result,TestBean.class));
            }
        };
    }
}
