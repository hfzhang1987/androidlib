package com.pengpeng.android.client.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title CollectionsUtils
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/22 17:51
 */

public class CollectionsUtil {

    /**
     * Make Array data to List.(DO NOT use Arrays.asList)
     */
    @SafeVarargs
    public static <T> List<T> asList(T... arrayData) {
        List<T> mList = new ArrayList<>();
        if (arrayData == null){
            return mList;
        }
        Collections.addAll(mList, arrayData);
        return mList;
    }


    public static <T> void addAllDistinct(List<T> src, List<T> des) {
        for (T item : des) {
            if (!src.contains(item)) {
                src.add(item);
            }
        }
    }

    public static <T> List<T> asList(Collection<T> data) {
        List<T> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }


}
