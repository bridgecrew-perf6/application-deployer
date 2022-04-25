package com.kovalenko.deployer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ApplicationDeployerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDeployerApplication.class, args);
	}

}
