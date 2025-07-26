package com.aydanjb.backpicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
})
public class BackpickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackpickerApplication.class, args);
	}

}
