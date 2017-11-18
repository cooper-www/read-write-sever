package com.after10years.java.readwritesever.aop;

import com.after10years.java.readwritesever.constants.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态数据源上下文
 */
public class DynamicDataSourceHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {

        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
