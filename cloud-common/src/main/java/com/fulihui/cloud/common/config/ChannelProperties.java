package com.fulihui.cloud.common.config;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by fulihui on 2018/1/24.
 */
@ConfigurationProperties(prefix = "coupons.platform")
public class ChannelProperties
{
    private List<String> channels = Lists.newArrayList();

    public List<String> getChannels()
    {
        return channels;
    }

    public void setChannels(List<String> channels)
    {
        this.channels = channels;
    }
}
