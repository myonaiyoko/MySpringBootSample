package com.myonaiyoko.service.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myonaiyoko.logic.todo.TodoLogic;
import com.myonaiyoko.model.todo.TodoModel;

@Service
public class TodoService {

	@Autowired
	private TodoLogic logic;
	
	public List<TodoModel> getAllTodo() {
		return logic.getAllTodo();
	}
	
	public List<TodoModel> getUserTodo(TodoModel model) {
		return logic.getUserTodo(model.getUserId());
	}
	
	public int addTodo(TodoModel model) {
		return logic.insertTodo(model);
	}
	
	public int updTodo(TodoModel model) {
		return logic.updateTodo(model);
	}
	
	public int delTodo(TodoModel model) {
		return logic.deleteTodo(model.getId());
	}
}
