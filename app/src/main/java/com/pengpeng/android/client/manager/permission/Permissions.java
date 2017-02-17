package com.pengpeng.android.client.manager.permission;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title ss
 * @Package com.pengpeng.android.client.manager.permisstion
 * http://blog.csdn.net/u013278099/article/details/50612266
 * @Description:
 * @date 2017/2/14 18:05
 */

/**
 * Enum class to handle the different states
 * of permissions since the PackageManager only
 * has a granted and denied state.
 */
enum Permissions  {
        //已授权，授权失败，未发现的权限
        GRANTED,
        DENIED,
        NOT_FOUND

}
