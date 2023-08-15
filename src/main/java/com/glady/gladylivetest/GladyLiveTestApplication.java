package com.glady.gladylivetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GladyLiveTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GladyLiveTestApplication.class, args);
	}

}
