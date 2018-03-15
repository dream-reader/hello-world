package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2017/12/26.
 */
@ConfigurationProperties(prefix = "dubbo.registry")
public class DubboRegistryProperties
{
    private String address;

    private String protocol;

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }
}
