package com.syr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class SyRV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SyRV1Application.class, args);
	}

}

