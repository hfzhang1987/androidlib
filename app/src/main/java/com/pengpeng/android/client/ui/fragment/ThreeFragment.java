package com.pengpeng.android.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.R;
import com.pengpeng.android.client.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title MyFragment
 * @Package com.pengpeng.android.client.ui.fragment
 * @Description:
 * @date 2017/2/15 13:42
 */

public class ThreeFragment extends BaseFragment {

    @BindView(R.id.tv_fragment)
    TextView tv_fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.e("执行了 ThreeFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("执行了 ThreeFragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e("执行了 ThreeFragment onCreateView");
        View root = inflater.inflate(R.layout.fragment1,container,false);
        ButterKnife.bind(this,root);
        tv_fragment.setText("ThreeFragment");
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("执行了 ThreeFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("执行了 ThreeFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("执行了 ThreeFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("执行了 ThreeFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e("执行了 ThreeFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("执行了 ThreeFragment onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("执行了 ThreeFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e("执行了 ThreeFragment onDetach");
    }
}
