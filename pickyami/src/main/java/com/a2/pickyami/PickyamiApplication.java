package com.a2.pickyami;

import com.a2.pickyami.game.enums.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication()
public class PickyamiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickyamiApplication.class, args);
	}

}
