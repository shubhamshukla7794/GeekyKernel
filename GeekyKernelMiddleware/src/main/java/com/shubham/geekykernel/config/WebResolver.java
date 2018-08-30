package com.shubham.geekykernel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.shubham")
public class WebResolver 
	{
		@Bean
		public InternalResourceViewResolver getViewResolver()
		{
			InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
			viewResolver.setPrefix("/WEB-INF/jsp/");
			return viewResolver;
		}
		@Bean
		public CommonsMultipartResolver multipartResolver() {
		    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		    resolver.setDefaultEncoding("utf-8");
		    return resolver;
		}

	}
