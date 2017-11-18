package com.after10years.java.readwritesever.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="writeDataSource", destroyMethod = "close", initMethod="init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        logger.info("初始化写入数据源……");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    /**
     * 有多少个从库就要配置多少个
     * @return
     */
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "custom.datasource.read")
    public DataSource readDataSource(){
        logger.info("初始化读数据源……");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources(){
        List<DataSource> dataSources=new ArrayList<>();
        dataSources.add(readDataSource());
        return dataSources;
    }
}
