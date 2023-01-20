package com.myonaiyoko.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async
	public void myAsync(String arg, long wait, List<String> list) {
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		list.add(arg);
	}
}
