package com.pet_project.backend_server;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableElasticsearchRepositories
@SpringBootApplication
public class BackendServerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("/home/yumimi/MIneServer/backend-server")
				.filename(".env")
				.load();
		dotenv.entries().forEach(entry->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(BackendServerApplication.class, args);
	}

}
