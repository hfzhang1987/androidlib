package com.pengpeng.android.client.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseFragment
 * @Package com.pengpeng.android.client.base
 * @Description:
 * @date 2017/2/15 13:41
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.e("执行了 BaseFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("执行了 BaseFragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e("执行了 BaseFragment onCreateView");
        return container;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("执行了 BaseFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("执行了 BaseFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("执行了 BaseFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("执行了 BaseFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e("执行了 BaseFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("执行了 BaseFragment onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("执行了 BaseFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e("执行了 BaseFragment onDetach");
    }

}
