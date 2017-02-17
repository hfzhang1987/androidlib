package com.pengpeng.android.client.manager.permission;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title PermissionsResultAction
 * @Package com.pengpeng.android.client.manager.permission
 * @Description:
 * @date 2017/2/15 11:22
 */

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *将实例传递给 requestpermissionsifnecessaryforresult方法。结
 *果将被送回你无论是ongranted（所有的权限已被授予），或ondenied（所需
 *的权限被拒绝）。
 *你把你的ongranted方法和通知功能,用户都在ondenied方法不工作。
 */
public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private Looper mLooper = Looper.getMainLooper();
    private final Set<String> mPermissions = new HashSet<String>(1);
    /**
     * Default Constructor
     */
    public PermissionsResultAction(){}
    /**
     *如果您是在后台线程中使用权限请求，则希望
     *回调是在UI线程中,通过looper刷新ui
     */
    public PermissionsResultAction(@NonNull Looper looper) {mLooper = looper;}

    /**
     *当所有权限已被用户授予,把所有你的权限敏感的代码，可以只需执行所需权限
     */
    public abstract void onGranted();

    /**
     *当一个权限被拒绝的时候调用 被拒绝的权限
     *
     * @param
     */
    public abstract void onDenied(String permission);


    /**
     * 忽视未发现的权限
     */
    @SuppressWarnings({})
    public synchronized boolean shouldIgnorePermissionNotFound(String permission) {
        return true;
    }

    /*
     *返回授权 结果
     */
    @CallSuper
    protected synchronized final boolean onResult(final @NonNull String permission, int result) {
        if (result == PackageManager.PERMISSION_GRANTED) {
            return onResult(permission, Permissions.GRANTED);
        } else {
            return onResult(permission, Permissions.DENIED);
        }

    }

    /**
     *当一个特定的权限已更改。
     *此方法将调用所有权限，所以该方法确定
     *如果授权影响到该状态，是否可以继续进行
     */
    @CallSuper
    protected synchronized final boolean onResult(final @NonNull String permission, Permissions result) {
        //先从权限列表里移除当前权限
        mPermissions.remove(permission);
        if (result == Permissions.GRANTED) {
            if (mPermissions.isEmpty()) {
                new Handler(mLooper).post(new Runnable() {
                    @Override
                    public void run() {
                        onGranted();
                    }
                });
                return true;
            }
        } else if (result == Permissions.DENIED) {//权限被拒
            new Handler(mLooper).post(new Runnable() {
                @Override
                public void run() {
                    onDenied(permission);
                }
            });
            return true;
        } else if (result == Permissions.NOT_FOUND) {
            if (shouldIgnorePermissionNotFound(permission)) {
                if (mPermissions.isEmpty()) {//权限为空
                    new Handler(mLooper).post(new Runnable() {
                        @Override
                        public void run() {
                            onGranted();//去授权
                        }
                    });
                    return true;
                }
            } else {
                new Handler(mLooper).post(new Runnable() {
                    @Override
                    public void run() {
                        //拒绝权限
                        onDenied(permission);
                    }
                });
                return true;
            }
        }
        return false;
    }

    /**
     *注册指定的权限对象的permissionsresultaction
     *让它知道哪些权限来查找更改。permissions名单
     *
     * @param
     */
    @CallSuper
    protected synchronized final void registerPermissions(@NonNull String[] perms) {
        //把名单加到权限集合里
        Collections.addAll(mPermissions, perms);
    }
}
