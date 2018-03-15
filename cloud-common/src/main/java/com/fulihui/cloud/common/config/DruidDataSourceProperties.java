package com.fulihui.cloud.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2018/2/28.
 */
@ConfigurationProperties(prefix = "druid.datasource")
@Getter
@Setter
public class DruidDataSourceProperties
{
    private String driverClass;

    private String jdbcUrl;

    private String userName;

    private String passWord;

    private int initialSize = 0;

    private int maxActive = 8;

    private int minIdle = 0;

    private int maxIdle = 8;

    private long maxWait = -1L;

    private String validationQuery;

    private int validationQueryTimeout;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean testWhileIdle;

    private boolean poolPreparedStatements;

    private boolean sharePreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private long timeBetweenEvictionRunsMillis;

    private long minEvictableIdleTimeMillis;

    private boolean defaultAutoCommit = true;
}
