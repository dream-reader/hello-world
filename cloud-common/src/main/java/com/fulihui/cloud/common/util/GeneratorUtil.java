package com.fulihui.cloud.common.util;

import java.util.Objects;

/**
 * Created by fulihui on 2018/3/2.
 */
public final class GeneratorUtil
{
    private GeneratorUtil()
    {

    }

    /**
     * 按照规则生成id
     * @param prefix
     * @param length
     * @param num
     * @return
     */
    public static String generator( String prefix, int length, long num)
    {
        prefix = Objects.isNull(prefix) ? "" : prefix;
        String generator = String.format(prefix + "%0" + length + "d", num);
        return generator;
    }
}
