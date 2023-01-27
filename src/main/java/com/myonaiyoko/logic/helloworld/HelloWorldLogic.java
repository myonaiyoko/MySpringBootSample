package com.myonaiyoko.logic.helloworld;

import java.util.List;

import com.myonaiyoko.dao.helloworld.HelloWorldDao;
import com.myonaiyoko.entity.helloworld.HelloWorldEntity;
import com.myonaiyoko.model.helloworld.HelloWorldModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldLogic {

	@Autowired
	private HelloWorldDao dao;

	public HelloWorldModel getHelloWorld() {
		List<HelloWorldEntity> list = null;
		String str;
		list = dao.selectAll();

		if (list.size() == 0) {
			str = "データ無し";
		} else {
			str = list.get(0).getHelloworld();
		}

		HelloWorldModel model = new HelloWorldModel();
		model.setMessage(str);

		return model;
	}

}
