package com.jlp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UploadPathConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadFolder = "d://uploadFiles/";
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + uploadFolder);
        super.addResourceHandlers(registry);
    }
}