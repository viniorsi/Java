package com.br.TravelEase.EmailSmsSender;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailSmsSenderApplication {

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(EmailSmsSenderApplication.class, args);
	}


	@PostConstruct
	public void init() {
		System.out.println("🔥 Aplicação rodando na porta: " + serverPort);
	}
}
