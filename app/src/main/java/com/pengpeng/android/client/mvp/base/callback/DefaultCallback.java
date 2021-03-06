package com.pengpeng.android.client.mvp.base.callback;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.api.server.BaseServer;
import com.pengpeng.android.client.mvp.view.ILoadView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title DefaultCallback
 * @Package com.pengpeng.android.client.mvp.base
 * @Description:
 * @date 2017/2/22 15:57
 */

public abstract class DefaultCallback<D> extends BaseCallBack {



    protected ILoadView<D> mILoadView;
    protected BaseServer mServer;
    private static final String TAG = "***Response***---->";
    private Call mCall;

    public DefaultCallback(Call call, ILoadView<D> loadView, BaseServer server){
        mILoadView = loadView;
        mCall = call;
        mServer = server;
    }

    @Override
    public void onLoadCache(long cacheTime, String cacheStr) {

    }
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (call.isCanceled()){
            Logger.e("Call is canceled!");
            return;
        }
        if (!response.isSuccessful()){
            Logger.e("Call is failed!");
            mILoadView.onFail(response.code(),TAG+response.message().toString());
            return;
        }
        try {
            String ObjectStr = response.body().string();
            List<D> list = parseResult(ObjectStr);
            if (list==null||list.size()==0){
                Logger.e("no data return!");
                mILoadView.onFail(BaseServer.NO_MORE,TAG + "#onResponse-> No More");
            }
            Logger.e(BaseServer.NO_MORE+TAG + "#onResponse-> No More"+list.toArray());
            mILoadView.onSuccess(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Logger.e(BaseServer.FAILD +TAG + "#onResponse->"+ t.toString());
        mILoadView.onFail(BaseServer.FAILD,TAG + t.toString());
    }
    protected abstract List<D> parseResult(String result);

}
