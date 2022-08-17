package com.plantity.server;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.domain.users.Users;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@EnableJpaAuditing
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);

		/*
		try {
			String plantName = "몬스테라";
			String plantType = "식물타입";
			int level = 1;
			String content = "몬스테라는 식물입니다.";
			String plantImage = "식물사진";
			String status = "식물상태";

			MyPlantSaveRequestDto requestDto = MyPlantSaveRequestDto.builder()
					.plantName(plantName)
					.plantType(plantType)
					.level(level)
					.content(content)
					.plantImage(plantImage)
					.status(status)
					.build();
		}
		catch (Exception e) {
			return;
		}
		finally {
			return;
		}
		*/


	}

}
