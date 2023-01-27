package com.myonaiyoko.dao.todo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.myonaiyoko.entity.todo.TodoEntity;

@Repository
public class TodoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<TodoEntity> selectAll() {

		String sql = "select id, user_id, memo, deadline, status from todo";
		SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql);

		List<TodoEntity> retList = new ArrayList<>();

		while (resultSet.next()) {
			TodoEntity row = new TodoEntity();
			row.setId(resultSet.getLong("id"));
			row.setUserId(resultSet.getString("user_id"));
			row.setMemo(resultSet.getString("memo"));
			row.setDeadline(resultSet.getDate("deadline"));
			row.setStatus(resultSet.getString("status"));
			retList.add(row);
		}
		return retList;
	}

	public List<TodoEntity> selectByUserId(String userId) {

		String sql = "select id, user_id, memo, deadline, status from todo where user_id = ?";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);

		List<TodoEntity> retList = new ArrayList<>();
		for (Map<String, Object> map : result) {
			TodoEntity row = new TodoEntity();
			row.setId((Long)map.get("id"));
			row.setUserId((String)map.get("user_id"));
			row.setMemo((String)map.get("memo"));
			row.setDeadline((Date)map.get("deadline"));
			row.setStatus((String)map.get("status"));
			retList.add(row);
		}

		return retList;
	}

	public int insertTodo(TodoEntity entity) {
		
		String sql = "insert into todo (user_id, memo, deadline, status) values (?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
				entity.getUserId(),
				entity.getMemo(),
				entity.getDeadline(),
				entity.getStatus());
	}

	public int updateTodo(TodoEntity entity) {
		
		String sql = "update todo set user_id = ?, memo = ?, deadline = ?, status = ? where id = ?";
		return jdbcTemplate.update(sql,
				entity.getUserId(),
				entity.getMemo(),
				entity.getDeadline(),
				entity.getStatus(),
				entity.getId());
	}

	public int deleteTodo(long id) {
		
		String sql = "delete from todo where id = ?";
		return jdbcTemplate.update(sql, id);
	}
}
