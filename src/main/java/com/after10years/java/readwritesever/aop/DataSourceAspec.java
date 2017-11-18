package com.after10years.java.readwritesever.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DataSourceAspec {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspec.class);

    @Before("execution(* com.after10years.java.readwritesever.mapper..*.select*(..)) || execution(* com.after10years.java.readwritesever.mapper.*.get*(..))")
    public void setReadDataSourceType() {
        DynamicDataSourceHolder.read();
        logger.info("dataSource切换到：Read");
    }

    @Before("execution(* com.after10years.java.readwritesever.mapper..*.insert*(..)) || execution(* com.after10years.java.readwritesever.mapper..*.update*(..))")
    public void setWriteDataSourceType() {
        DynamicDataSourceHolder.write();
        logger.info("dataSource切换到：write");
    }
}
