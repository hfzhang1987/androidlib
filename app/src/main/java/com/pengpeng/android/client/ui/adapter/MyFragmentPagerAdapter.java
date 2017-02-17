package com.pengpeng.android.client.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pengpeng.android.client.ui.activity.TwoActivity;
import com.pengpeng.android.client.ui.fragment.FiveFragment;
import com.pengpeng.android.client.ui.fragment.FourFragment;
import com.pengpeng.android.client.ui.fragment.MyFragment;
import com.pengpeng.android.client.ui.fragment.MyTwoFragment;
import com.pengpeng.android.client.ui.fragment.SevenFragment;
import com.pengpeng.android.client.ui.fragment.SixFragment;
import com.pengpeng.android.client.ui.fragment.ThreeFragment;

import java.util.ArrayList;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title adapter
 * @Package com.pengpeng.android.client.ui
 * @Description:
 * @date 2017/2/15 16:01
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> tabList;


    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabList) {
        super(fm);
        this.tabList = tabList;
    }


    @Override
    public Fragment getItem(int position) {
         if (tabList.get(position).equals("aaa")){
             return new MyFragment();
         }else if(tabList.get(position).equals("bbb")) {
             return new MyTwoFragment();
         }else{
             return new ThreeFragment();
         }
    }

    @Override
    public int getCount() {
        return tabList.size();
    }
}
