package com.learning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages = {"com.learning"})
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver("WEB-INF/view/", ".jsp"));
    }
}
