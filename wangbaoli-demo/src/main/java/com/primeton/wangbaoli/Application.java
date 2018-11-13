package com.primeton.wangbaoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages=("com.primeton.wangbaoli"))
public class Application extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	@Override
	  //修改启动类，继承 SpringBootServletInitializer并重写 configure方法
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
	    //注意这里要指向原先用main方法执行的Application启动类
	    return builder.sources(Application.class);
	  }
	

}
