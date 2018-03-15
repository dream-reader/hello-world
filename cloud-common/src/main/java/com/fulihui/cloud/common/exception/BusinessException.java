package com.fulihui.cloud.common.exception;

import com.fulihui.cloud.common.enums.ErrorEnum;

/**
 * Created by fulihui on 2018/1/8.
 */
public class BusinessException extends RuntimeException
{
    private String code = ErrorEnum.SYSTEM_ERROR.getCode();

    public BusinessException()
    {
        super();
    }

    public BusinessException(String message)
    {
        super(message);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BusinessException(String code, String message)
    {
        super(message);
        this.code = code;
    }

    protected BusinessException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
