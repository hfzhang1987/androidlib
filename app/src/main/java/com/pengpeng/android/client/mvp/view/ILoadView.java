package com.pengpeng.android.client.mvp.view;

import java.util.List;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title ILoadView
 * @Package com.pengpeng.android.client.mvp.view
 * @Description:
 * @date 2017/2/22 11:26
 */

public interface ILoadView<D>{

    void onSuccess(List<D> data);

    void onCache(long cacheTime,List<D> data);

    void onFail(int errorCode,String errorMsg);

}
