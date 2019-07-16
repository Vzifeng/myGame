package com.example.game.dataSource;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 13:55 2019/07/16
 * @Version ： $version$
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源切换处理
 */
public class DynamicDataSourceContextHolder {

    public static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    //使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
    //所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal <>();

    //设置数据源的变量
    public static void setDateSourceType(String dsTypes){
        LOGGER.info("切换到{}数据源",dsTypes);
        CONTEXT_HOLDER.set(dsTypes);
    }

    //获取数据源的变量
    public static String getDateSourceType(){
        return CONTEXT_HOLDER.get();
    }

    //清空数据源变量
    public static void clearDateSourceType(){
        CONTEXT_HOLDER.remove();
    }

}
