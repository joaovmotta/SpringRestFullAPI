package br.com.example.SpringBootH2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class SpringBootH2Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2Application.class, args);
	}
}
