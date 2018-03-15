package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2018/1/3.
 */
@ConfigurationProperties(prefix = "redis.config")
public class RedisProperties
{
    private Integer maxIdle = 8;

    private Integer maxTotal = 8;

    private Integer minIdle = 0;

    private String host;

    private Integer port;

    private Integer timeOut;

    private String passWord;

    public Integer getMaxIdle()
    {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle)
    {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxTotal()
    {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal)
    {
        this.maxTotal = maxTotal;
    }

    public Integer getMinIdle()
    {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle)
    {
        this.minIdle = minIdle;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public Integer getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut)
    {
        this.timeOut = timeOut;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
}
