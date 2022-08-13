package com.plantity.server;

import com.plantity.server.domain.users.Users;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(UsersRepository userRepository, UsersService userService){
		return (args) -> {
			userRepository.save(new Users("nickname1", "social1", "rating1", 1, "status1"));
		};
	}

	 */
}
