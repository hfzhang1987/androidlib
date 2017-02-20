package com.pengpeng.android.client.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.R;
import com.pengpeng.android.client.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestActivity
 * @Package com.pengpeng.android.client.ui.activity
 * @Description:
 * @date 2017/2/9 17:52
 */

public class TestActivity extends BaseActivity {

    /**
     * 1、强大的View绑定和Click事件处理功能，简化代码，提升开发效率
     2、方便的处理Adapter里的ViewHolder绑定问题
     3、运行时不会影响APP效率，使用配置方便
     4、代码清晰，可读性强
     */
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn.setText("跳转act");
        Logger.e("执行了 onCreate");
    }


    @OnClick(R.id.btn)
    public void upToAct(){
        Intent intent = new Intent(this,TwoActivity.class);
        startActivity(intent);
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
}
