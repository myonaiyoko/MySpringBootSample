package com.myonaiyoko.dao;

import java.util.ArrayList;
import java.util.List;

import com.myonaiyoko.entity.HelloWorldEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class HelloWorldDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<HelloWorldEntity> selectAll() {
		String sql = "SELECT helloworld FROM helloworld";
		SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql);
		
		List<HelloWorldEntity> retList = new ArrayList<>();
		
		while (resultSet.next()) {
			HelloWorldEntity row = new HelloWorldEntity();
			row.setHelloworld(resultSet.getString("helloworld"));
			retList.add(row);
		}
		return retList;
	}
}
