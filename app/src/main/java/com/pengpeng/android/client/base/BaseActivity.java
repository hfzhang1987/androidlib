package com.pengpeng.android.client.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseActivity
 * @Package com.pengpeng.android.client.base
 * @Description:
 * @date 2017/2/9 17:52
 */

public class BaseActivity extends AppCompatActivity implements StatisticsInterface{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
