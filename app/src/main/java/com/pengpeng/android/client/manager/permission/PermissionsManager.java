package com.pengpeng.android.client.manager.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title PermissionsManager
 * @Package com.pengpeng.android.client.manager.permission
 * @Description:
 * @date 2017/2/15 10:55
 */

public class PermissionsManager {

    private static final String TAG = PermissionsManager.class.getSimpleName();

    private final Set<String> mPendingRequests = new HashSet<String>(1);
    private final Set<String> mPermissions = new HashSet<String>(1);
    private final List<WeakReference<PermissionsResultAction>> mPendingActions = new ArrayList<WeakReference<PermissionsResultAction>>(1);

    private static PermissionsManager mInstance = null;

    public static PermissionsManager getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionsManager();
        }
        return mInstance;
    }

    private PermissionsManager() {
        initializePermissionsMap();
    }

    /**
     * 此方法使用反射来读取清单类中的所有权限。
     *因为一些权限不存在于旧版本的安卓系统中，
     *因为他们不存在，他们将被拒绝时，你检查是否有权限
     *因为一个新的权限，往往是补充，那里没有以前的
     *所需的许可。我们初始化一组可用的权限，并检查组
     *检查是否有权限，当我们被拒绝时权限仍然不存在
     *
     */
    private synchronized void initializePermissionsMap() {
        Field[] fields = Manifest.permission.class.getFields();
        for (Field field : fields) {
            String name = null;
            try {
                name = (String) field.get("");
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Could not access field", e);
            }
            mPermissions.add(name);
        }
    }

    /**
     * 此方法检索在应用程序清单中声明的所有权限。
     * 它返回一个非空数组，可以声明的权限。
     *
     * @param  检查我们需要哪些权限
     * @return 返回在应用程序清单中声明的非空数组
     */
    @NonNull
    private synchronized String[] getManifestPermissions(@NonNull final Activity activity) {
        PackageInfo packageInfo = null;
        List<String> list = new ArrayList<String>(1);
        try {
            Log.d(TAG, activity.getPackageName());
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "A problem occurred when retrieving permissions", e);
        }
        if (packageInfo != null) {
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                for (String perm : permissions) {
                    Log.d(TAG, "Manifest contained permission: " + perm);
                    list.add(perm);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 在permissionsresultaction对象，它将变更通知这些权限
     * @param 权限所需的操作的权限。
     * @param 将 动作添加到当前正在执行的动作列表中。
     */
    private synchronized void addPendingAction(@NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        if (action == null) {
            return;
        }
        action.registerPermissions(permissions);
        mPendingActions.add(new WeakReference<PermissionsResultAction>(action));
    }

    /**
     * 删除从队列中等待的动作和执行该动作
     * @param 移除动作
     */
    private synchronized void removePendingAction(@Nullable PermissionsResultAction action) {
        for (Iterator<WeakReference<PermissionsResultAction>> iterator = mPendingActions.iterator();
             iterator.hasNext(); ) {
            WeakReference<PermissionsResultAction> weakRef = iterator.next();
            if (weakRef.get() == action || weakRef.get() == null) {
                iterator.remove();
            }
        }
    }

    /**
     *这个静态方法可以用来检查你是否有一个特定的权限。
     *
     * @param 检查权限的上下文对象
     * @param 要检查的权限
     * @return 返回是否授权了此权限
     */
    public synchronized boolean hasPermission(@Nullable Context context, @NonNull String permission) {
        return context != null && (ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED || !mPermissions.contains(permission));
    }

    /**
     * 这个静态方法可以用来检查你是否有几个特定的权限。
     * 为每一个权限，并将简单地返回一个布尔值是否你有所有的权限
     * @param 需要检查 权限的上下文
     * @param 权限 数组
     * @return 返回你是否有所有的权限
     */
    public synchronized boolean hasAllPermissions(@Nullable Context context, @NonNull String[] permissions) {
        if (context == null) {
            return false;
        }
        boolean hasAllPermissions = true;
        for (String perm : permissions) {
            hasAllPermissions &= hasPermission(context, perm);
        }
        return hasAllPermissions;
    }

    /**
     *这种方法的是获取清单里面的所有权限。permissionsresultaction  用于通知允许用户允许或拒绝每一个权限。
     *
     * @param 需要检查权限的activity
     * @param permissionsresultaction用于权限接受通知你
     */
    public synchronized void requestAllManifestPermissionsIfNecessary(final @Nullable Activity activity,
                                                                      final @Nullable PermissionsResultAction action) {
        if (activity == null) {
            return;
        }
        String[] perms = getManifestPermissions(activity);
        requestPermissionsIfNecessaryForResult(activity, perms, action);
    }

    /**
     *该方法将请求的权限，如果
     *他们需要被要求（即我们没有许可），并会增加
     * permissionsresultaction到队列被通知的权限被授予或
     *否认。在预Android棉花糖的情况下，将立即授予权限。
     *活动变量为空，但如果它是无效的，不能执行的方法。
     *这是唯一可作为一种礼貌片段，getactivity()可能产量空
     *如果该片段没有添加到其父活动中
     * @param 请求权限的活动
     * @param 权限列表
     * @param permissionsresultaction通知当权限授予或拒绝。
     */
    public synchronized void requestPermissionsIfNecessaryForResult(@Nullable Activity activity,
                                                                    @NonNull String[] permissions,
                                                                    @Nullable PermissionsResultAction action) {
        if (activity == null) {
            return;
        }
        addPendingAction(permissions, action);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            doPermissionWorkBeforeAndroidM(activity, permissions, action);
        } else {
            List<String> permList = getPermissionsListToRequest(activity, permissions, action);
            if (permList.isEmpty()) {
                //if there is no permission to request, there is no reason to keep the action int the list
                removePendingAction(action);
            } else {
                String[] permsToRequest = permList.toArray(new String[permList.size()]);
                mPendingRequests.addAll(permList);
                ActivityCompat.requestPermissions(activity, permsToRequest, 1);
            }
        }
    }

    /**
     * 该方法将请求的权限，如果
     *他们需要被要求（即我们没有许可），并会增加
     * permissionsresultaction到队列被通知的权限被授予或
     *否认。在预Android棉花糖(6.0)的情况下，将立即授予权限。
     *但如果 getactivity()返回null，这方法
     *将无法工作作为活动引用，必须检查权限。
     * @param 需要检查 权限的fragmnet
     * @param 需要请求的权限列表
     * @param permissionsresultaction通知当权限授予或拒绝。
     */
    public synchronized void requestPermissionsIfNecessaryForResult(@NonNull Fragment fragment, @NonNull String[] permissions,
                                                                    @Nullable PermissionsResultAction action) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        addPendingAction(permissions, action);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            doPermissionWorkBeforeAndroidM(activity, permissions, action);
        } else {
            List<String> permList = getPermissionsListToRequest(activity, permissions, action);
            if (permList.isEmpty()) {
                //if there is no permission to request, there is no reason to keep the action int the list
                removePendingAction(action);
            } else {
                String[] permsToRequest = permList.toArray(new String[permList.size()]);
                mPendingRequests.addAll(permList);
                fragment.requestPermissions(permsToRequest, 1);
            }
        }
    }

    /**
     * 这个方法通知permissionsmanager，权限已经改变。如果你正在做
     *使用活动的权限请求，则应调用此方法,将通知所有悬而未决  的,permissionsresultaction当前对象
     *在队列中，并将从挂起的请求列表中删除权限请求。
     * @param  已更改的权限。
     * @param  每个权限的值
     */
    public synchronized void notifyPermissionsChange(@NonNull String[] permissions, @NonNull int[] results) {
        int size = permissions.length;
        if (results.length < size) {
            size = results.length;
        }
        Iterator<WeakReference<PermissionsResultAction>> iterator = mPendingActions.iterator();
        while (iterator.hasNext()) {
            PermissionsResultAction action = iterator.next().get();
            for (int n = 0; n < size; n++) {
                if (action == null || action.onResult(permissions[n], results[n])) {
                    iterator.remove();
                    break;
                }
            }
        }
        for (int n = 0; n < size; n++) {
            mPendingRequests.remove(permissions[n]);
        }
    }

    /**
     * 在安卓设备前要求权限（安卓6，api23）,根据权限状态，直接进行或拒绝工作
     * @param 检测权限的activity
     * @param 权限数组
     * @param 我们执行某项操作后权限检查
     */
    private void doPermissionWorkBeforeAndroidM(@NonNull Activity activity, @NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        for (String perm : permissions) {
            if (action != null) {
                if (!mPermissions.contains(perm)) {
                    action.onResult(perm, Permissions.NOT_FOUND);
                } else if (ActivityCompat.checkSelfPermission(activity, perm)
                        != PackageManager.PERMISSION_GRANTED) {
                    action.onResult(perm, Permissions.DENIED);
                } else {
                    action.onResult(perm, Permissions.GRANTED);
                }
            }
        }
    }

    /**
     * @param 检查权限的activity
     * @param 所有权限的名字
     * @param 我们执行某项操作后权限检查
     * @return 尚未授予的权限名称列表
     */
    @NonNull
    private List<String> getPermissionsListToRequest(@NonNull Activity activity,
                                                     @NonNull String[] permissions,@Nullable PermissionsResultAction action) {
        List<String> permList = new ArrayList<String>(permissions.length);
        for (String perm : permissions) {
            if (!mPermissions.contains(perm)) {
                if (action != null) {
                    action.onResult(perm, Permissions.NOT_FOUND);
                }
            } else if (ActivityCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
                if (!mPendingRequests.contains(perm)) {
                    permList.add(perm);
                }
            } else {
                if (action != null) {
                    action.onResult(perm,Permissions.GRANTED);
                }
            }
        }
        return permList;
    }

}
