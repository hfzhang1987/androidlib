package com.pengpeng.android.client.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.mvp.presenter.LoadPresenter;
import com.pengpeng.android.client.ui.controller.BaseActController;

import butterknife.ButterKnife;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseActivity
 * @Package com.pengpeng.android.client.base
 * @Description:模板模式
 * @date 2017/2/9 17:52
 */

public abstract class BaseActivity extends AppCompatActivity implements StatisticsInterface{

    protected LoadPresenter mLoadPresenter;

    protected abstract int layoutId();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        onSetTheme();
        super.onCreate(savedInstanceState);
        if (layoutId()!=0){
            setContentView(layoutId());
        }else {
            new Throwable("You need to add the parent layout!!!!!!!!");
        }
        ButterKnife.bind(this);
        Logger.e("执行了 onCreate");
    }

    //设置自定义主题
    protected void onSetTheme() {
        //自定义风格
        //setTheme();
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
        mLoadPresenter.onDestroy();
        Logger.e("执行了 onDestroy");

    }

}
