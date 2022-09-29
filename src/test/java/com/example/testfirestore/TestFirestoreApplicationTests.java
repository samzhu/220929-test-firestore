package com.example.testfirestore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DatastoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;

import reactor.core.publisher.Mono;

@Testcontainers
@SpringBootTest
class TestFirestoreApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Container
	private static final DatastoreEmulatorContainer datastoreEmulator = new DatastoreEmulatorContainer(
			DockerImageName.parse("gcr.io/google.com/cloudsdktool/cloud-sdk:317.0.0-emulators"));

	@DynamicPropertySource
	static void emulatorProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.cloud.gcp.datastore.host", () -> "http://" + datastoreEmulator.getEmulatorEndpoint());
	}

	@TestConfiguration
	static class EmulatorConfiguration {
		// By default, autoconfiguration will initialize application default
		// credentials.
		// For testing purposes, don't use any credentials. Bootstrap w/
		// NoCredentialsProvider.
		@Bean
		CredentialsProvider googleCredentials() {
			return NoCredentialsProvider.create();
		}
	}

	@Test
	void contextLoads() {
		User u = new User();
		u.setName("Ray");
		Mono<User> saved = userRepository.save(u);
		assertNotNull(saved.block().getName());

	}

}
