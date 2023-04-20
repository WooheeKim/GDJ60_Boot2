package com.iu.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//일반적인 자바 객체가 아니라 설정파일임을 나타내는 객체
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${app.upload.base}")
	private String basePath;
	
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath)
				.addResourceLocations(basePath);
	}
	
}
