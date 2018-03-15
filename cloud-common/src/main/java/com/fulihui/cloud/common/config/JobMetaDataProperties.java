package com.fulihui.cloud.common.config;

/**
 * Created by fulihui on 2018/1/2.
 */
public class JobMetaDataProperties
{
    private String jobName;

    private String jobClass;

    private String cron;

    private Integer shardingTotalCount = 1;

    private String shardingItemParameters;

    private boolean overWrite;

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobClass()
    {
        return jobClass;
    }

    public void setJobClass(String jobClass)
    {
        this.jobClass = jobClass;
    }

    public String getCron()
    {
        return cron;
    }

    public void setCron(String cron)
    {
        this.cron = cron;
    }

    public int getShardingTotalCount()
    {
        return shardingTotalCount;
    }

    public void setShardingTotalCount(int shardingTotalCount)
    {
        this.shardingTotalCount = shardingTotalCount;
    }

    public String getShardingItemParameters()
    {
        return shardingItemParameters;
    }

    public void setShardingItemParameters(String shardingItemParameters)
    {
        this.shardingItemParameters = shardingItemParameters;
    }

    public boolean isOverWrite()
    {
        return overWrite;
    }

    public void setOverWrite(boolean overWrite)
    {
        this.overWrite = overWrite;
    }
}
