package com.pengpeng.android.client.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.base.Config;
import com.pengpeng.android.client.mvp.view.ILoadView;
import com.pengpeng.android.client.utils.DateUtil;
import com.pengpeng.android.client.utils.MD5Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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
    public HashMap<String,String> hashMap;

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

    protected  HashMap setRequestParams(HashMap hashMap){
        hashMap.put("_timestamp", DateUtil.diffDate(new Date(), DateUtil.utc2Local("1970-01-01 00:00:00"))+"");
        Set set = hashMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>(set);
        Collections.sort(keyList, new Comparator<String>() {
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });
        StringBuilder sbs=new StringBuilder();
        sbs.append(Config._SECRET);
        for(String key:keyList){
            if(hashMap.containsKey(key))
                sbs.append(key).append(hashMap.get(key));
        }
        sbs.append(Config._SECRET);
        Logger.e("setRequestParams _sign ==> " + sbs);
        hashMap.put("_sign",MD5Util.getMD5Str(sbs.toString()).toUpperCase());
        return hashMap;
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

    public void onDestroy(){
        mCall.cancel();
        mCallback =null;
        hashMap = null;
    }
}
