package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ImportResource({"classpath:dubbo.xml"})
public class SpringBootServlet extends SpringBootServletInitializer {

	// jar启动
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringBootServlet.class, args);
    }

	// tomcat war启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootServlet.class);
    }

}
