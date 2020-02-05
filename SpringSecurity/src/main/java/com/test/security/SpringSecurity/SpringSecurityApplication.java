package com.test.security.SpringSecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
    	return args -> {
    		repository.save(new Book("Love Story", "Mg Myo Min", new BigDecimal("15.54")));
    		repository.save(new Book("Zkabat Facebook", "Bamor Thein Phea", new BigDecimal("13.3")));
    		repository.save(new Book("Sherlock Home", "Shwe oo Daung", new BigDecimal("150.54")));
		};

	}

}
