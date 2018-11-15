package com.primeton.wangbaoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot启动类
 * 
 * @author root
 *
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = ("com.primeton.wangbaoli"))
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}
