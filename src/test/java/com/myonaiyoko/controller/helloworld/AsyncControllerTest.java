package com.myonaiyoko.controller.helloworld;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myonaiyoko.service.helloworld.HelloWorldService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AsyncControllerTest {

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
	void URLがmyasyncのGETのテスト() throws Exception {
		MvcResult result = mockMvc.perform(
				get("/async/myasync"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("132", resString);
	}

}
