package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemplatesApplication {
	public static void main(String[] args) {

		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(TemplatesApplication.class, args);
	}

}
