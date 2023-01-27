package com.myonaiyoko.controller.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myonaiyoko.model.helloworld.HelloWorldModel;
import com.myonaiyoko.service.helloworld.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorldController {

	@Autowired
	private HelloWorldService service;

	@RequestMapping("/controller")
	public String getStringFromController() throws JsonProcessingException {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!");

		return convertJson(model);
	}

	@RequestMapping("/service")
	public String getStringFromService() throws JsonProcessingException {
		HelloWorldModel model = service.getMessage(); 

		return convertJson(model);
	}

	@RequestMapping("/db")
	public String getHelloWorldDb() throws JsonProcessingException {
		HelloWorldModel model = service.getMessageDB(); 

		return convertJson(model);
	}
	
	private String convertJson(HelloWorldModel model) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(model);
	}
}
