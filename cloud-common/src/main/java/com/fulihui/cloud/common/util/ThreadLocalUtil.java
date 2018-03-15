package com.fulihui.cloud.common.util;

/**
 * Created by fulihui on 2018/1/19.
 */
public final class ThreadLocalUtil
{
    private ThreadLocalUtil()
    {

    }

    /**
     * 后期可以用json对象做多字段传输，ImplicitMetaParam
     */
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void set(String implicit)
    {
        local.set(implicit);
    }

    public static String get()
    {
        return local.get();
    }

    public static void remove()
    {
        local.remove();
    }
}
