package br.com.corujabet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CorujabetApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorujabetApplication.class, args);
	}

}
