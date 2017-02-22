package com.pengpeng.android.client.mvp.presenter;

import com.pengpeng.android.client.mvp.view.ILoadView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title LoadPresenter
 * @Package com.pengpeng.android.client.mvp.presenter
 * @Description:
 * @date 2017/2/22 11:19
 */
public abstract class LoadPresenter<D> {
    private Call mCall;
    private Callback mCallback;

    public LoadPresenter(){
        initAPI();
    }

    protected abstract void initAPI();

    protected void loadData(boolean fromCache, Call<ResponseBody> call, ILoadView<D> loadView){
        if (fromCache){
            doCacheLoading(call,loadView);
        }else{
            doRemoteLoading(call,loadView);

        }
    }

    protected void doCacheLoading(Call call,ILoadView<D> loadView){

    }

    protected void doRemoteLoading(Call call,ILoadView<D> loadView){
        mCall = call;
        mCallback= createCallBack(mCall,loadView);
        if (mCallback!=null)
        mCall.enqueue(mCallback);
    }

    protected abstract Callback createCallBack(Call call, ILoadView<D> loadView);

}
