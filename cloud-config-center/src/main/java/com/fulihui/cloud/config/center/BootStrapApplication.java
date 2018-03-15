package com.fulihui.cloud.config.center;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringConfigServerApplication
public class BootStrapApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(BootStrapApplication.class).web(true).run(args);
	}
}
