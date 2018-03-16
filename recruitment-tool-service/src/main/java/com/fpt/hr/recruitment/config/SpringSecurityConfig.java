package com.fpt.hr.recruitment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("com.fpt.hr.recruitment.security")
public class SpringSecurityConfig {
	public SpringSecurityConfig() {
		super();
	}
}
