package com.example.testfirestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;

@EnableCaching
@EnableReactiveFirestoreRepositories
@SpringBootApplication
public class TestFirestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestFirestoreApplication.class, args);
	}

}
