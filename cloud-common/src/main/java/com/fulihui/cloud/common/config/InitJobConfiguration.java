package com.fulihui.cloud.common.config;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;

/**
 * Created by fulihui on 2018/1/2.
 */
public class InitJobConfiguration
{
    private Map<String, JobMetaDataProperties> jobMeta;

    private ApplicationContext applicationContext;

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public void init() throws Exception
    {
        Class<?> jobClass = null;
        JobScheduler jobScheduler = null;
        LiteJobConfiguration liteJobConfiguration = null;
        if (Objects.isNull(jobMeta))
        {
            return;
        }
        for (Map.Entry<String, JobMetaDataProperties> entry : jobMeta.entrySet())
        {
            jobClass = Class.forName(entry.getValue().getJobClass());
            liteJobConfiguration = LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                    entry.getKey(), entry.getValue().getCron(), entry.getValue().getShardingTotalCount())
                    .shardingItemParameters(entry.getValue().getShardingItemParameters()).build(), jobClass.getCanonicalName()))
                    .overwrite(entry.getValue().isOverWrite()).build();
            jobScheduler = new SpringJobScheduler((ElasticJob) applicationContext.getBean(jobClass), zookeeperRegistryCenter, liteJobConfiguration);
            jobScheduler.init();
        }
    }

    public Map<String, JobMetaDataProperties> getJobMeta()
    {
        return jobMeta;
    }

    public void setJobMeta(Map<String, JobMetaDataProperties> jobMeta)
    {
        this.jobMeta = jobMeta;
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public ZookeeperRegistryCenter getZookeeperRegistryCenter()
    {
        return zookeeperRegistryCenter;
    }

    public void setZookeeperRegistryCenter(ZookeeperRegistryCenter zookeeperRegistryCenter)
    {
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }
}
