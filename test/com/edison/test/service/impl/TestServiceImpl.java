package com.edison.test.service.impl;



import org.springframework.stereotype.Service;

import com.edison.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Override
	public void say() {
		System.out.println("spring say hi.");
	}

}
