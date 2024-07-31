package com.namji.datacollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class DataCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectionApplication.class, args);
	}

}
