package com.pengpeng.android.client.ui.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title BaseActController
 * @Package com.pengpeng.android.client.ui.controller
 * @Description:
 * @date 2017/2/20 18:01
 */

public class BaseActController {

    private static BaseActController mINSTANCE  = new BaseActController();

    private BaseActController() {
    }

    public static BaseActController getInstance(){
        synchronized (BaseActController.class){
            return mINSTANCE;
        }
    }

    public Fragment getVisibleFragment(FragmentActivity context){
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null)
            return null;
        for(Fragment fragment : fragments){
            if(fragment != null && fragment.isVisible()&& fragment.getUserVisibleHint())
                return fragment;
        }
        return null;
    }

    protected void onClickSmoothScroll(FragmentActivity context) {
        Fragment fragment = getVisibleFragment(context);
//        if (fragment != null && fragment instanceof ScrollFragment) {
//            ((ScrollFragment) fragment).smoothScrollTo(0);
//        }
    }
}
