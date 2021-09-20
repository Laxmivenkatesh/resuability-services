package com.resuability;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.resuability")
@SpringBootApplication(scanBasePackages = {"com.resuability"})
public class ResuabilityServicesApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResuabilityServicesApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ResuabilityServicesApplication.class, args);
    }

}