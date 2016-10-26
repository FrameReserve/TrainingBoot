package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootServlet extends SpringBootServletInitializer {

	// jar启动
	public static void main(String[] args) {
        SpringApplication.run(SpringBootServlet.class, args);
    }

	// tomcat war启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootServlet.class);
    }

}
