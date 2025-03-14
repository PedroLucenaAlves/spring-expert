package io.projetospring.expert.arquiteturaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LivrariaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaRestApplication.class, args);

	}

}
