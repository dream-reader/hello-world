package com.fulihui.cloud.common.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fulihui on 2017/12/26.
 */
@Configuration
@EnableConfigurationProperties({DubboAnnotationProperties.class})
public class DubboConfiguration
{
    @Bean(name = "registry")
    @ConfigurationProperties(prefix = "dubbo.registry")
    @Order(1)
    public RegistryConfig registryConfig()
    {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }

    @Bean(name = "application")
    @ConfigurationProperties(prefix = "dubbo.appconfig")
    @Order(1)
    public ApplicationConfig applicationConfig()
    {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    @Bean(name = "protocol")
    @ConfigurationProperties(prefix = "dubbo.protocol")
    @Order(1)
    public ProtocolConfig protocolConfig()
    {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        return protocolConfig;
    }

    @Bean(name = "provider")
    @ConfigurationProperties(prefix = "dubbo.provider")
    @Order(2)
    public ProviderConfig providerConfig()
    {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setApplication(applicationConfig());
        providerConfig.setRegistry(registryConfig());
        List<ProtocolConfig> protocols = new ArrayList<>();
        protocols.add(protocolConfig());
        providerConfig.setProtocols(protocols);
        return providerConfig;
    }

    /**
     * 如果过早加载，会导致服务service注解过早加载，但是dubbo的基本配置bean还没有初始化
     * @return
     */
    @Bean(name = "annotation")
    @Order(3)
    public AnnotationBean annotation(DubboAnnotationProperties dubboAnnotationProperties)
    {
        AnnotationBean annotation = new AnnotationBean();
        annotation.setPackage(dubboAnnotationProperties.getScanPackage());
        return annotation;
    }
}
