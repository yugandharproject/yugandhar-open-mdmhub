package com.yugandhar.YugandharBootProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({"com.yugandhar.*"})
@EntityScan({"com.yugandhar.*"})
@EnableJpaRepositories({"com.yugandhar.*"})
@EnableCaching
@EnableAspectJAutoProxy
@EnableJms
@EnableAsync
public class YugandharBootProjectApplication {
//public class YugandharBootProjectApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(YugandharBootProjectApplication.class, args);
	}
	
	}
