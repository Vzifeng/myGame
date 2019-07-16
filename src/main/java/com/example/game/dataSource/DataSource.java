package com.example.game.dataSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 13:52 2019/07/16
 * @Version ： $version$
 */

/**
 *自定义多数据源切换注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    public DataSourceType value() default DataSourceType.MASTER;

}
