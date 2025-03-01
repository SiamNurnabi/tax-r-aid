package com.example.taxraid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "uploaded-files";
        Path fileDir = Paths.get(location);

        String filePath = fileDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + location + "/**")
                .addResourceLocations("file:" + filePath + "/");
    }
}
