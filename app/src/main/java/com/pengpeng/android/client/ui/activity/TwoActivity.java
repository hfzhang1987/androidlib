package com.pengpeng.android.client.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.R;
import com.pengpeng.android.client.base.BaseActivity;
import com.pengpeng.android.client.ui.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TwoActivity
 * @Package com.pengpeng.android.client.ui.activity
 * @Description:
 * @date 2017/2/15 14:07
 */

public class TwoActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<String> tabList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_act);
        ButterKnife.bind(this);
        Logger.e("执行了 TwoActivity onCreate");
        tabList.add("aaa");
        tabList.add("bbb");
        tabList.add("ccc");
//        for (int i = 0; i < 1; i++) {
//            tabList.add(11111*i+"");
//        }
        //为TabLayout添加tab名称
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(3)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(4)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(5)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(6)));

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),tabList);

        // 第二种方式添加tab时使用
//        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        // 一站式管理方式：setupWithViewPager()方法底部会调用FragmentPagerAdapter中的getPageTitle()方法.
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.e("执行了 TwoActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.e("执行了 TwoActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.e("执行了 TwoActivity onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Logger.e("执行了 TwoActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.e("执行了 TwoActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("执行了 TwoActivity onDestroy");
    }
}
