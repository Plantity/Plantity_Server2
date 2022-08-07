package com.plantity.server;

import com.plantity.server.domain.Users;
import com.plantity.server.domain.UsersRequestDto;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsersRepository userRepository, UsersService userService){
		return (args) -> {
			userRepository.save(new Users("nickname1", "social1", "rating1", 1, "status1"));
			List<Users> userList = userRepository.findAll();

			UsersRequestDto userRequestDto = new UsersRequestDto("nickname2", "social2", "rating2", 2, "status2");
			userService.updateUser(1L, userRequestDto);
			userList = userRepository.findAll();


		};
	}
}
