package com.cv.mailguntest.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com.cv.mailguntest"})
public class MailguntestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailguntestApplication.class, args);
	}
}
