package com.fulihui.cloud.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by fulihui on 2017/12/26.
 */
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
@EnableSwagger2
public class SwaggerConfiguration
{
    @Bean
    public Docket createRestApi(SwaggerProperties swaggerProperties, ApiInfo apiInfo)
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getScan()))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo(SwaggerProperties swaggerProperties)
    {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .build();
    }
}
