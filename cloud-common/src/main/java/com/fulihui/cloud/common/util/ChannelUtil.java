package com.fulihui.cloud.common.util;

import java.util.List;
import java.util.Objects;

/**
 * Created by fulihui on 2018/1/24.
 */
public final class ChannelUtil
{
    private ChannelUtil()
    {

    }

    public static boolean checkChannel(List<String> channels, String channel)
    {
        return Objects.nonNull(channels) && Objects.nonNull(channel) && channels.contains(channel);
    }
}
