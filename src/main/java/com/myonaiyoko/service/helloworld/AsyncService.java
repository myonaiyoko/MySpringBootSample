package com.myonaiyoko.service.helloworld;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async
	public void myAsync(String arg, long wait, List<String> list) throws InterruptedException {
		Thread.sleep(wait);
		list.add(arg);
	}
}
