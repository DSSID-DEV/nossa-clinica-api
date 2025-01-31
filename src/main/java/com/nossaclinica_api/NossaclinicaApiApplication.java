package com.nossaclinica_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "nossa-clinica-api", version = "1", description = "API desenvolvido para prover recursos para os usuários das aplicações web e mobile da empresa NOSSA CLÍNICA"))
public class NossaclinicaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossaclinicaApiApplication.class, args);
	}

}
