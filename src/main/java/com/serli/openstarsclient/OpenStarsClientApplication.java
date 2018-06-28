package com.serli.openstarsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({"com.serli.openstarsclient"})
@EntityScan("com.serli.openstarsclient.data")
@EnableJpaRepositories("com.serli.openstarsclient.repository")
public class OpenStarsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenStarsClientApplication.class, args);
	}
}
