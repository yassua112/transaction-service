package com.transaction.stroreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.transaction.stroreservice.model.entity")
@EnableJpaRepositories("com.transaction.stroreservice.repository")
@ComponentScan(basePackages = { "com.transaction.stroreservice.*" })
@SpringBootApplication
public class StroreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StroreServiceApplication.class, args);
	}

}
