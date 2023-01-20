package com.myonaiyoko.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.myonaiyoko.DBBackUpper;
import com.myonaiyoko.entity.HelloWorldEntity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	DbUnitTestExecutionListener.class,
	TransactionalTestExecutionListener.class })
@TestPropertySource(locations = "/test.properties")
class HelloWorldDaoTest extends DBBackUpper {

	@Autowired
	private HelloWorldDao dao;

	@BeforeAll
	static void initAll() {
		testTables = new String[] { "helloworld" };
		dbBackup();
	}

	@AfterAll
	static void tearDownAll() {
		dbRestore();
	}

	@Test
	@DatabaseSetup("/dbunit/helloworld/HelloWorldTest01.xml")
	void helloworld取得テスト() {
		List<HelloWorldEntity> list = dao.selectAll();

		assertEquals(1, list.size(), "データ取得1件のみの確認");
		assertEquals("helloworld!!!", list.get(0).getHelloworld(), "取得文字の確認");
	}

}
