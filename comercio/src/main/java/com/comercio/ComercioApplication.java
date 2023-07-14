package com.comercio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ComercioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComercioApplication.class, args);
	}

}
