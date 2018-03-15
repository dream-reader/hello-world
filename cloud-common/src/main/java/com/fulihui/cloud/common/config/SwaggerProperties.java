package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fulihui on 2017/12/26.
 */
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties
{
    private String scan;

    private String title;

    private String description;

    private String version;

    public String getScan()
    {
        return scan;
    }

    public void setScan(String scan)
    {
        this.scan = scan;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}
