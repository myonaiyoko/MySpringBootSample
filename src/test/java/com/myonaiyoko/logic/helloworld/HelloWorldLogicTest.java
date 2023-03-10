package com.myonaiyoko.logic.helloworld;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.myonaiyoko.dao.helloworld.HelloWorldDao;
import com.myonaiyoko.entity.helloworld.HelloWorldEntity;
import com.myonaiyoko.model.helloworld.HelloWorldModel;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HelloWorldLogicTest {

	@InjectMocks
	HelloWorldLogic logic;

	@Mock
	HelloWorldDao dao;
	
	@Test
	void helloworld取得テスト() {
		List<HelloWorldEntity> list = new ArrayList<HelloWorldEntity>();
		HelloWorldEntity entity = new HelloWorldEntity();
		entity.setHelloworld("helloworld!!!");
		list.add(entity);
		doReturn(list).when(dao).selectAll();
		
		HelloWorldModel model = logic.getHelloWorld();

		assertEquals("helloworld!!!", model.getMessage());
	}

	@Test
	void helloworld0件テスト() {
		List<HelloWorldEntity> list = new ArrayList<HelloWorldEntity>();
		doReturn(list).when(dao).selectAll();
		
		HelloWorldModel model = logic.getHelloWorld();

		assertEquals("データ無し", model.getMessage());
	}
}
