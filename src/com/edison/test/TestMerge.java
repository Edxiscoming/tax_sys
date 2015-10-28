package com.edison.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edison.test.service.TestService;



public class TestMerge {

	@Test
	public void testSpring() {
		ClassPathXmlApplicationContext ctf=		new ClassPathXmlApplicationContext("applicationContext.xml");
		TestService testService=(TestService) ctf.getBean("testService");
		testService.say();
	}

}
