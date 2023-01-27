package com.myonaiyoko.controller.todo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.myonaiyoko.DBBackUpper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	DbUnitTestExecutionListener.class,
	TransactionalTestExecutionListener.class })
@TestPropertySource(locations = "/test.properties")
public class TodoControllerTest extends DBBackUpper {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@BeforeAll
	static void initAll() throws FileNotFoundException, SQLException, DatabaseUnitException, IOException {
		testTables = new String[] { "todo" };
		dbBackup();
	}

	@AfterAll
	static void tearDownAll() throws DatabaseUnitException, SQLException, IOException {
		dbRestore();
	}

	@BeforeEach
	void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	@Order(1)
	@DatabaseSetup("/dbunit/todo/TodoTest01.xml")
	void Todo全データ取得のテスト() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/todo/all")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("[{\"id\":1,\"userId\":\"00001\",\"memo\":\"1st memo\",\"deadline\":\"2023-01-28\",\"status\":\"complete\"},{\"id\":2,\"userId\":\"00001\",\"memo\":\"2nd memo\",\"deadline\":\"2023-01-28\",\"status\":\"unfinished\"},{\"id\":3,\"userId\":\"00002\",\"memo\":\"other user memo\",\"deadline\":\"2023-01-28\",\"status\":\"unfinished\"}]", resString);
	}

	
	@Test
	@Order(2)
	@DatabaseSetup("/dbunit/todo/TodoTest01.xml")
	void Todoユーザー指定データ取得のテスト() throws Exception {
		String body = "{\"userId\":\"00001\"}";
		MvcResult result = mockMvc.perform(
				post("/todo/usertodo")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		
		assertEquals("[{\"id\":1,\"userId\":\"00001\",\"memo\":\"1st memo\",\"deadline\":\"2023-01-28\",\"status\":\"complete\"},{\"id\":2,\"userId\":\"00001\",\"memo\":\"2nd memo\",\"deadline\":\"2023-01-28\",\"status\":\"unfinished\"}]", resString);
	}
	
	@Test
	@Order(3)
	void Todoデータ追加テスト() throws Exception {
		String body = "{\"id\":\"1\",\"userId\":\"00001\",\"memo\":\"insert test memo\",\"deadline\":\"2022-02-01\",\"status\":\"未完\"}";
		MvcResult result = mockMvc.perform(
				post("/todo/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		assertEquals("1", resString);
	}
	
	@Test
	@Order(4)
	void Todoデータ更新テスト() throws Exception {
		String body = "{\"id\":\"1\",\"userId\":\"00001\",\"memo\":\"insert test memo\",\"deadline\":\"2022-02-01\",\"status\":\"未完\"}";
		MvcResult result = mockMvc.perform(
				post("/todo/upd")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		assertEquals("1", resString);
	}
	
	@Test
	@Order(5)
	void Todoデータ削除テスト() throws Exception {
		String body = "{\"id\":\"1\"}";
		MvcResult result = mockMvc.perform(
				post("/todo/del")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String resString = result.getResponse().getContentAsString();
		assertEquals("1", resString);
	}
}
