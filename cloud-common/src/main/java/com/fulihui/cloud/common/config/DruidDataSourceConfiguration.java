package com.fulihui.cloud.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by fulihui on 2018/2/28.
 */
@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidDataSourceConfiguration
{
    @Bean
    public DataSource dataSource(DruidDataSourceProperties druidDataSourceProperties)
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidDataSourceProperties.getDriverClass());
        dataSource.setUrl(druidDataSourceProperties.getJdbcUrl());
        dataSource.setUsername(druidDataSourceProperties.getUserName());
        dataSource.setPassword(druidDataSourceProperties.getPassWord());
        dataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
        dataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
        dataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
        //dataSource.setMaxIdle(druidDataSourceProperties.getMaxIdle());
        dataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
        dataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
        dataSource.setValidationQueryTimeout(druidDataSourceProperties.getValidationQueryTimeout());
        dataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
        dataSource.setSharePreparedStatements(druidDataSourceProperties.isSharePreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        dataSource.setDefaultAutoCommit(druidDataSourceProperties.isDefaultAutoCommit());
        return dataSource;
    }
}
