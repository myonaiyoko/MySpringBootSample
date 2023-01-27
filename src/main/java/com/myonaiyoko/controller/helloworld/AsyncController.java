package com.myonaiyoko.controller.helloworld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myonaiyoko.service.helloworld.AsyncService;

@RestController
@RequestMapping("/async")
@EnableAsync
public class AsyncController {

	@Autowired
	private AsyncService service;
	
	@RequestMapping("/myasync")
	public String myAsync() throws InterruptedException, ExecutionException {
		
		List<String> list = new ArrayList<>();

		service.myAsync("1", 0, list);
		service.myAsync("2", 1000, list);
		service.myAsync("3", 100, list);
		
		Thread.sleep(2000);
		
		return list.get(0) + list.get(1) + list.get(2);
	}
}
