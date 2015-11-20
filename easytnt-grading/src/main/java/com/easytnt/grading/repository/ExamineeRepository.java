package com.easytnt.grading.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.easytnt.commons.entity.repository.Repository;
import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

public interface ExamineeRepository extends Repository{
	int insertImports(JdbcTemplate jdbcTemplate,ListDataMapper mapper, ListDataSourceReader reader) throws Exception;
}