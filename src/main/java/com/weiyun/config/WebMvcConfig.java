package com.weiyun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Starix
 * @date 2021/3/7 15:17
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final static String FILE_PATH = "E:\\upload\\";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + FILE_PATH);
    }
}

