package com.easytnt.grading.service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ExamineeService{
	int insertImports(JdbcTemplate jdbcTemplate,ListDataMapper mapper,ListDataSourceReader reader) throws Exception;
}
