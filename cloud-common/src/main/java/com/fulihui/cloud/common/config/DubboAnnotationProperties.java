package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2017/12/26.
 */
@ConfigurationProperties(prefix = "dubbo.annotation")
public class DubboAnnotationProperties
{
    private String scanPackage;

    public String getScanPackage()
    {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage)
    {
        this.scanPackage = scanPackage;
    }
}
