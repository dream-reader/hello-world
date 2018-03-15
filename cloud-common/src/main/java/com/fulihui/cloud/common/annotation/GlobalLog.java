package com.fulihui.cloud.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface GlobalLog
{
	String value() default "INFO";
	
	boolean input() default true;
	
	boolean output() default true;
}
