package com.after10years.java.readwritesever.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DataSourceTransactionManager.class);

    @Resource(name = "writeDataSource")
    private DataSource dataSource;
    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        logger.info("transactionManager 初始化……");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
}
