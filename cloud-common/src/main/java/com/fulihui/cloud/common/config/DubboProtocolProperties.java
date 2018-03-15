package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2017/12/26.
 */
@ConfigurationProperties(prefix = "dubbo.protocol")
public class DubboProtocolProperties
{
    private String name;

    private Integer port;

    private Integer threads;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public int getThreads()
    {
        return threads;
    }

    public void setThreads(int threads)
    {
        this.threads = threads;
    }
}
