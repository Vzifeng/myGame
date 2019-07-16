package com.example.game.dataSource;

import org.aspectj.bridge.Message;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:15 2019/07/16
 * @Version ： $version$
 * 多数据源处理
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.example.game.dataSource.DataSource)")
    public void dsPointcut(){

    }

    @Around("dsPointcut()")
    public Object around(ProceedingJoinPoint point){

        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);

        if (dataSource != null){
            DynamicDataSourceContextHolder.setDateSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDateSourceType();
        }
        return null;
    }
}
