package com.myonaiyoko.logic.todo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myonaiyoko.dao.todo.TodoDao;
import com.myonaiyoko.entity.todo.TodoEntity;
import com.myonaiyoko.model.todo.TodoModel;

@Component
public class TodoLogic {

	@Autowired
	private TodoDao dao;
	
	public List<TodoModel> getAllTodo() {
		List<TodoModel> list = entityListToListModel(dao.selectAll());
		return list;
	}

	public List<TodoModel> getUserTodo(String userId) {
		List<TodoModel> list = entityListToListModel(dao.selectByUserId(userId));
		return list;
	}
	
	public int insertTodo(TodoModel model) {
		TodoEntity entity = modelToEntity(model);
		return dao.insertTodo(entity);
	}
	
	public int updateTodo(TodoModel model) {
		TodoEntity entity = modelToEntity(model);
		return dao.updateTodo(entity);
	}
	
	public int deleteTodo(long id) {
		return dao.deleteTodo(id);
	}
	
	private TodoEntity modelToEntity(TodoModel model) {
		TodoEntity entity = new TodoEntity();
		entity.setId(model.getId());
		entity.setUserId(model.getUserId());
		entity.setMemo(model.getMemo());
		entity.setDeadline(Date.valueOf(model.getDeadline()));
		entity.setStatus(model.getStatus());

		return entity;
	}
	
	private List<TodoModel> entityListToListModel(List<TodoEntity> entityList) {
		List<TodoModel> modelList = new ArrayList<>();
		
		for (TodoEntity entity : entityList) {
			TodoModel model = new TodoModel();
			model.setId(entity.getId());
			model.setUserId(entity.getUserId());
			model.setMemo(entity.getMemo());
			model.setDeadline(String.valueOf(entity.getDeadline()));
			model.setStatus(entity.getStatus());
			modelList.add(model);
		}
		return modelList;
	}
}
