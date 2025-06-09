package com.br.sorteio.sorteio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.br.sorteio.sorteio.repository")
public class SorteioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorteioApplication.class, args);
	}

}
