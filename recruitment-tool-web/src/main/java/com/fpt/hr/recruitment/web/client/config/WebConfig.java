package com.fpt.hr.recruitment.web.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource("classpath:WebMvcConfig.xml")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	public WebConfig(){
		super();
	}
}
