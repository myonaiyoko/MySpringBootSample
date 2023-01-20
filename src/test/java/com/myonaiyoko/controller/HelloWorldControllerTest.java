package com.myonaiyoko.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myonaiyoko.model.HelloWorldModel;
import com.myonaiyoko.service.HelloWorldService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class HelloWorldControllerTest {

	@MockBean
	private HelloWorldService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	@Order(1)
	void URLがcontrollerのGETのテスト() throws Exception {
		MvcResult result = mockMvc.perform(
				get("/HelloWorld/controller"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!\"}", resString);
	}

	@Test
	@Order(2)
	void URLがcontrollerのPOSTのテスト() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/HelloWorld/controller"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!\"}", resString);
	}

	@Test
	@Order(3)
	void URLがserviceのGETのテスト() throws Exception {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!");
		doReturn(model).when(service).getMessage();
		
		MvcResult result = mockMvc.perform(
				get("/HelloWorld/service"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!!\"}", resString);
	}

	@Test
	@Order(4)
	void URLがserviceのPOSTのテスト() throws Exception {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!");
		doReturn(model).when(service).getMessage();
		
		MvcResult result = mockMvc.perform(
				post("/HelloWorld/service"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!!\"}", resString);
	}

	@Test
	@Order(5)
	void URLがdbのGETのテスト() throws Exception {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!!");
		doReturn(model).when(service).getMessageDB();
		
		MvcResult result = mockMvc.perform(
				get("/HelloWorld/db"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!!!\"}", resString);
	}

	@Test
	@Order(6)
	void URLがdbのPOSTのテスト() throws Exception {
		HelloWorldModel model = new HelloWorldModel();
		model.setMessage("HelloWorld!!!");
		doReturn(model).when(service).getMessageDB();
		
		MvcResult result = mockMvc.perform(
				post("/HelloWorld/db"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("{\"message\":\"HelloWorld!!!\"}", resString);
	}
}
