package com.alimmit.reactlistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReactListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactListApiApplication.class, args);
	}
}
