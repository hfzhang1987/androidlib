package com.pengpeng.android.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title DateUtil
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/23 17:33
 */

public class DateUtil {

    /**
     * 功能描述：日期相减
     *
     * @param date Date 日期
     * @param date1 Date 日期
     * @return 返回相减后的日期
     */
    public static Long diffDate(Date date, Date date1) {
        return getMillis(date) - getMillis(date1);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    public static Date utc2Local(String utctime){
        SimpleDateFormat utcFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate=null;
        try {
            gpsUTCDate=utcFormat.parse(utctime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat localFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localFormat.setTimeZone(TimeZone.getDefault());
        String localTime=localFormat.format(gpsUTCDate.getTime());

        try {
            return localFormat.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
