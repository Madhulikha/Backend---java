package com.weclear.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api1/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://weclearfrontend.s3-website.eu-north-1.amazonaws.com",
                        "https://weclear.in",
                        "https://dkcci0rugat9s.cloudfront.net")

                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}