package com.sulimann.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PicPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicPayApplication.class, args);
	}

}
