package com.luis.desafiont;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
@OpenAPIDefinition(info = @Info(title = "DesafioNT API", version = "1.0", description = "DesafioNT"))
public class DesafiontApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiontApplication.class, args);
	}

}
