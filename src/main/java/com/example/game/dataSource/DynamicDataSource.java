package com.example.game.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:02 2019/07/16
 * @Version ： $version$
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


   public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object,Object> targetDataSources){
       super.setDefaultTargetDataSource(defaultTargetDataSource);
       super.setTargetDataSources(targetDataSources);
       super.afterPropertiesSet();
   }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSourceType();
    }
}
