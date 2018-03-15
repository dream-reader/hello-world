package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by fulihui on 2018/1/2.
 */
@ConfigurationProperties(prefix = "elastic.job")
public class ElasticJobProperties
{
    private String serverLists;

    private String namespace;

    private Integer baseSleepTimeMilliseconds = 1000;

    private Integer maxSleepTimeMilliseconds = 3000;

    private Integer maxRetries = 3;

    private Integer sessionTimeoutMilliseconds;

    private Integer connectionTimeoutMilliseconds;

    private String digest;

    private Map<String, JobMetaDataProperties> jobMeta;

    public String getServerLists()
    {
        return serverLists;
    }

    public void setServerLists(String serverLists)
    {
        this.serverLists = serverLists;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }

    public Integer getBaseSleepTimeMilliseconds()
    {
        return baseSleepTimeMilliseconds;
    }

    public void setBaseSleepTimeMilliseconds(Integer baseSleepTimeMilliseconds)
    {
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
    }

    public Integer getMaxSleepTimeMilliseconds()
    {
        return maxSleepTimeMilliseconds;
    }

    public void setMaxSleepTimeMilliseconds(Integer maxSleepTimeMilliseconds)
    {
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
    }

    public Integer getMaxRetries()
    {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries)
    {
        this.maxRetries = maxRetries;
    }

    public Integer getSessionTimeoutMilliseconds()
    {
        return sessionTimeoutMilliseconds;
    }

    public void setSessionTimeoutMilliseconds(Integer sessionTimeoutMilliseconds)
    {
        this.sessionTimeoutMilliseconds = sessionTimeoutMilliseconds;
    }

    public Integer getConnectionTimeoutMilliseconds()
    {
        return connectionTimeoutMilliseconds;
    }

    public void setConnectionTimeoutMilliseconds(Integer connectionTimeoutMilliseconds)
    {
        this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
    }

    public String getDigest()
    {
        return digest;
    }

    public void setDigest(String digest)
    {
        this.digest = digest;
    }

    public Map<String, JobMetaDataProperties> getJobMeta()
    {
        return jobMeta;
    }

    public void setJobMeta(Map<String, JobMetaDataProperties> jobMeta)
    {
        this.jobMeta = jobMeta;
    }
}
