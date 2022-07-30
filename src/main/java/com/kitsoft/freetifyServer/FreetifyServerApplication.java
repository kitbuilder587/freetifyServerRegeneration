package com.kitsoft.freetifyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FreetifyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreetifyServerApplication.class, args);
	}

}
