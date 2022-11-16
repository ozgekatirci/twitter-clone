package com.ozgekatirci.TwitterClone;

import com.ozgekatirci.TwitterClone.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TwitterCloneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1= new User();
		user1.setNameAndSurname("ozge katirci");

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}



}
