package com.github.melonticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class MelonticketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelonticketApplication.class, args);
	}

}
