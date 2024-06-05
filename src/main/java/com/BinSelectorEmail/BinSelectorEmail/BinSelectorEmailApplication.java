package com.BinSelectorEmail.BinSelectorEmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BinSelectorEmailApplication {
    

	public static void main(String[] args) {
		SpringApplication.run(BinSelectorEmailApplication.class, args);
	}

}
