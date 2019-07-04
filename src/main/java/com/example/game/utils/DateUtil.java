package com.example.game.utils;

import org.springframework.util.unit.DataUnit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 12:49 2019/5/22
 * @Version ： $version$
 */
public class DateUtil {

    /**
     * date转yyyyMMddHHmmss类型字符串
     * @param date
     * @return
     */
    public static String dateToString1(Date date){
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
        String stringDate = sm.format(date);
        return stringDate;
    }
    /**
     * date转yyyy-MM-dd HH:mm:ss类型字符串
     * @param date
     * @return
     */
    public static String dateToString2(Date date){
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sm.format(date);
        return stringDate;
    }

    /**
     * 获取指定年月的第一天
     * @param str
     * @return
     */
    public static String getFirstDayOfMonth(String str) {
        int year = Integer.parseInt(str.substring(0,4));
        int month = Integer.parseInt(str.substring(4,6));
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     * @param str
     * @return
     */
    public static String getLastDayOfMonth(String str) {
        int year = Integer.parseInt(str.substring(0,4));
        int month = Integer.parseInt(str.substring(4,6));
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(DateUtil.dateToString1(date));
        System.out.println(DateUtil.dateToString2(date));
    }
}
