package com.myonaiyoko.service;

import com.myonaiyoko.logic.HelloWorldLogic;
import com.myonaiyoko.model.HelloWorldModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	@Autowired
	private HelloWorldLogic logic;
	
	public HelloWorldModel getMessage() {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!");

		return model;
	}
	
	public HelloWorldModel getMessageDB() {
		return logic.getHelloWorld();
	}
}
