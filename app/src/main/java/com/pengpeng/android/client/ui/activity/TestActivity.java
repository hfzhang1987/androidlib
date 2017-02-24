package com.pengpeng.android.client.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.R;
import com.pengpeng.android.client.base.BaseActivity;
import com.pengpeng.android.client.mvp.model.TestBean;
import com.pengpeng.android.client.mvp.presenter.TestPresenter;
import com.pengpeng.android.client.mvp.view.ILoadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestActivity
 * @Package com.pengpeng.android.client.ui.activity
 * @Description:
 * @date 2017/2/9 17:52
 */

public class TestActivity extends BaseActivity implements ILoadView<TestBean> {

    /**
     * 1、强大的View绑定和Click事件处理功能，简化代码，提升开发效率
     2、方便的处理Adapter里的ViewHolder绑定问题
     3、运行时不会影响APP效率，使用配置方便
     4、代码清晰，可读性强
     */
    @BindView(R.id.btn)
    Button btn;
    private TestPresenter testPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn.setText("跳转act");
        Logger.e("执行了 onCreate");
        testPresenter = new TestPresenter();
        testPresenter.login(this);
    }

    @OnClick(R.id.btn)
    public void upToAct(){
//        Intent intent = new Intent(this,TwoActivity.class);
//        startActivity(intent);
        testPresenter.login(this);
        Logger.e("获取网络数据===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.e("执行了 onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.e("执行了 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.e("执行了 onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Logger.e("执行了 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.e("执行了 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("执行了 onDestroy");
    }

    @Override
    public void onSuccess(List<TestBean> data) {
        Logger.e(data.get(0).getId());
    }

    @Override
    public void onCache(long cacheTime, List<TestBean> data) {
        Logger.e("缓存");
    }

    @Override
    public void onFail(int errorCode, String errorMsg) {
        Logger.e(errorCode+"======>"+errorMsg);
    }
}
