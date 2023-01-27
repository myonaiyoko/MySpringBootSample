package com.myonaiyoko.service.helloworld;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableAsync
class AsyncServiceTest {

	@Autowired
	private AsyncService service;
	
	@Test
	void test() throws InterruptedException, ExecutionException {

		List<String> list = new ArrayList<>();

		service.myAsync("1", 0, list);
		service.myAsync("2", 1000, list);
		service.myAsync("3", 100, list);
		
		Thread.sleep(2000);
		
		assertEquals("132", list.get(0) + list.get(1) + list.get(2));
	}

}
