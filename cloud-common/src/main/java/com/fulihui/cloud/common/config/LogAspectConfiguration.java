package com.fulihui.cloud.common.config;

import com.fulihui.cloud.common.annotation.GlobalLog;
import com.fulihui.cloud.common.annotation.IgnoreLog;
import com.fulihui.cloud.common.annotation.MethodLog;
import com.fulihui.cloud.common.enums.SeparatorEnum;
import com.fulihui.cloud.common.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by fulihui on 2017/12/26.
 */
@Configuration
public class LogAspectConfiguration
{
    @Aspect
    @Component
    @Order(-5)
    public static class WebLogAspect
    {
        private static Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

        ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

        ThreadLocal<String> levelThreadLocal = new ThreadLocal<String>();

        @Pointcut(value = "execution(public * com..*.controller..*.*(..))")
        public void webLog()
        {

        }

        @Before("webLog()")
        public void doBefore(JoinPoint joinPoint)
        {
            startTimeThreadLocal.set(System.currentTimeMillis());
            Class<?> cls = joinPoint.getTarget().getClass();
            GlobalLog globalLog = cls.getAnnotation(GlobalLog.class);
            if (Objects.isNull(globalLog))
            {
                startTimeThreadLocal.remove();
                return;
            }
            String level = globalLog.value().toUpperCase();
            boolean input = globalLog.input();
            boolean output = globalLog.output();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            Class<?>[] clss = new Class<?>[args.length];
            StringBuilder inputSb = new StringBuilder();
            if (Objects.nonNull(args))
            {
                for (int i = 0; i < args.length; i++)
                {
                    clss[i] = args[i].getClass();
                    if (input)
                    {
                        inputSb.append(", ");
                        inputSb.append( args[i].getClass().getSimpleName());
                        inputSb.append(": {}");
                    }
                }
            }
            try
            {
                Method method = cls.getMethod(methodName, clss);
                MethodLog methodLog = method.getAnnotation(MethodLog.class);
                IgnoreLog ignoreLog = method.getAnnotation(IgnoreLog.class);
                if (Objects.nonNull(ignoreLog))
                {
                    startTimeThreadLocal.remove();
                    return;
                }
                if (Objects.nonNull(methodLog))
                {
                    level = methodLog.value().toUpperCase();
                    input = methodLog.input();
                    output = methodLog.output();
                }
            }
            catch (Exception e)
            {
                LOGGER.error("WebLogAspect error : {}", e);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getSimpleName());
            sb.append("-->");
            sb.append(joinPoint.getSignature().getName());
            sb.append(", run start input param : ");
            if (input)
            {
                sb.append(inputSb.toString());
            }
            else
            {
                sb.append("{}");
            }
            switch (level)
            {
                case "TRACE":
                    LOGGER.trace(sb.toString(), input ? args : "");
                    break;
                case "DEBUG":
                    LOGGER.debug(sb.toString(), input ? args : "");
                    break;
                case "INFO":
                    LOGGER.info(sb.toString(), input ? args : "");
                    break;
                case "WARN":
                    LOGGER.warn(sb.toString(), input ? args : "");
                    break;
                case "ERROR":
                    LOGGER.error(sb.toString(), input ? args : "");
                    break;
                default:
                    LOGGER.debug(sb.toString(), input ? args : "");
                    break;
            }
            levelThreadLocal.set(StringUtil.join(new String[]{level, output ? "true" : "false"}, SeparatorEnum.COLON.getValue()));
        }

        @AfterReturning(value = "webLog()", returning = "returnValue")
        public void doAfterReturning(JoinPoint joinPoint, Object returnValue)
        {
            Long start = startTimeThreadLocal.get();
            String str = levelThreadLocal.get();
            String level = "DEBUG";
            boolean output = true;
            if (Objects.nonNull(start))
            {
                String[] strs = str.split(SeparatorEnum.COLON.getValue());
                level = strs[0] == null ? "DEBUG" : strs[0];
                output = Boolean.valueOf(strs[1] == null ? "true" : strs[1]);
                Class<?> cls = joinPoint.getTarget().getClass();
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getSimpleName());
                sb.append("-->");
                sb.append(joinPoint.getSignature().getName());
                sb.append(", run end output : {}");
                sb.append(", total run time {} ms");
                switch (level)
                {
                    case "TRACE":
                        LOGGER.trace(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                    case "DEBUG":
                        LOGGER.debug(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                    case "INFO":
                        LOGGER.info(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                    case "WARN":
                        LOGGER.warn(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                    case "ERROR":
                        LOGGER.error(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                    default:
                        LOGGER.debug(sb.toString(), output ? returnValue : "", System.currentTimeMillis() - start);
                        break;
                }
                startTimeThreadLocal.remove();
                levelThreadLocal.remove();
            }
        }
    }
}
