package com.sparta.pineapple.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://applyy.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("*")
                .exposedHeaders("Authorization", "Refresh-Token");
    }

}