package com.myonaiyoko.controller.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myonaiyoko.model.todo.TodoModel;
import com.myonaiyoko.service.todo.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@PostMapping("/all")
	@ResponseBody
	public List<TodoModel> getAllTodoList() {
		return service.getAllTodo();
	}

	@PostMapping("/usertodo")
	@ResponseBody
	public List<TodoModel> getUseTodoList(@RequestBody TodoModel model) {
		return service.getUserTodo(model);
	}

	@PostMapping("/add")
	public int addTodo(@RequestBody TodoModel model) {
		return service.addTodo(model);
	}

	@PostMapping("/upd")
	public int updTodo(@RequestBody TodoModel model) {
		return service.updTodo(model);
	}

	@PostMapping("/del")
	public int del(@RequestBody TodoModel model) {
		return service.delTodo(model);
	}

}
