package com.myonaiyoko.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myonaiyoko.logic.HelloWorldLogic;
import com.myonaiyoko.model.HelloWorldModel;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class HelloWorldServiceTest {

	@InjectMocks
	private HelloWorldService service;
	
	@Mock
	private HelloWorldLogic logic;
	
	@Test
	@Order(1)
	void getStringメソッドのテスト() {
		assertEquals("HelloWorld!!", service.getMessage().getMessage());
	}
	
	@Test
	@Order(2)
	void getStringFromDbメソッドのテスト() {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!!");
		doReturn(model).when(logic).getHelloWorld();
		
		assertEquals("HelloWorld!!!", service.getMessageDB().getMessage());
	}

}
