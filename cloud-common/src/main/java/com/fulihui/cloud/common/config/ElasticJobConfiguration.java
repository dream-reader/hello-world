package com.fulihui.cloud.common.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * Created by fulihui on 2018/1/2.
 */
@Configuration
@EnableConfigurationProperties({ElasticJobProperties.class})
public class ElasticJobConfiguration implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Bean(name = "zookeeperConfiguration")
    public ZookeeperConfiguration zookeeperConfiguration(ElasticJobProperties elasticJobProperties)
    {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(elasticJobProperties.getServerLists(), elasticJobProperties.getNamespace());
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(elasticJobProperties.getBaseSleepTimeMilliseconds());
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(elasticJobProperties.getMaxSleepTimeMilliseconds());
        zookeeperConfiguration.setMaxRetries(elasticJobProperties.getMaxRetries());
        if (Objects.nonNull(elasticJobProperties.getSessionTimeoutMilliseconds()))
        {
            zookeeperConfiguration.setSessionTimeoutMilliseconds(elasticJobProperties.getSessionTimeoutMilliseconds());
        }
        if (Objects.nonNull(elasticJobProperties.getConnectionTimeoutMilliseconds()))
        {
            zookeeperConfiguration.setConnectionTimeoutMilliseconds(elasticJobProperties.getConnectionTimeoutMilliseconds());
        }
        zookeeperConfiguration.setDigest(elasticJobProperties.getDigest());
        return zookeeperConfiguration;
    }

    @Bean(name = "zookeeperRegistryCenter", initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration zookeeperConfiguration)
    {
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

    @Bean(name = "initJobConfiguration", initMethod = "init")
    public InitJobConfiguration initJobConfiguration(ZookeeperRegistryCenter zookeeperRegistryCenter, ElasticJobProperties elasticJobProperties) throws Exception
    {
        InitJobConfiguration initJobConfiguration = new InitJobConfiguration();
        initJobConfiguration.setApplicationContext(applicationContext);
        initJobConfiguration.setJobMeta(elasticJobProperties.getJobMeta());
        initJobConfiguration.setZookeeperRegistryCenter(zookeeperRegistryCenter);
        return initJobConfiguration;
    }

    private DefaultListableBeanFactory getBeanFactory()
    {
       return (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}
