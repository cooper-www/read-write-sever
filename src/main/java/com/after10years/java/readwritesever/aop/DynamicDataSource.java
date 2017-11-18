package com.after10years.java.readwritesever.aop;

import com.after10years.java.readwritesever.constants.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public DynamicDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DynamicDataSourceHolder.getJdbcType();
        if (typeKey.equals(DataSourceType.write.getType()))
            return DataSourceType.write.getType();
        // 读 简单负载均衡
        int number = count.getAndAdd(1);
        return number % dataSourceNumber;
    }

}
