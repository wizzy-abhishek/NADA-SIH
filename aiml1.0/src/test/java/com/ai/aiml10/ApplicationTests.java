package com.ai.aiml10;

import com.ai.aiml10.entity.UserEntity;
import com.ai.aiml10.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private JWTService jwtService ;

	@Test
	void contextLoads() {

		UserEntity userEntity = new UserEntity( "abhishek.com" , "abhishek@123" , "abhishek");
		String token = jwtService.generateToken(userEntity);
		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);
		System.out.println(id);
	}

}
