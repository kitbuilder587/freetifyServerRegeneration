package com.kitsoft.freetifyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FreetifyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreetifyServerApplication.class, args);
	}

}
