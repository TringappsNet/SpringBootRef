package com_springcore_core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Config.class).build().run();
		assertNotNull(context);
		context.close();
	}

}
