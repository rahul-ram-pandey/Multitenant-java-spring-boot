package com.commutec.shuttle.traccar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PathMatchingConfigurationAdapter
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    HandlerInterceptor tenantInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor);
    }
}