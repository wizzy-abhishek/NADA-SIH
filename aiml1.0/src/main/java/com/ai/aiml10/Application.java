package com.ai.aiml10;

import com.ai.aiml10.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Hello AI");

		System.out.println("Done");
	}

}
