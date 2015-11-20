package com.easytnt.grading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.easytnt.grading.repository.ExamineeRepository;
import com.easytnt.grading.service.ExamineeService;
import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

@Service
public class ExamineeServiceImpl implements ExamineeService{
	@Autowired(required = false)
	private ExamineeRepository examineeRepository;

	@Override
	public int insertImports(JdbcTemplate jdbcTemplate,ListDataMapper mapper, ListDataSourceReader reader) throws Exception {
		int i=0;
		try{
			i = examineeRepository.insertImports(jdbcTemplate,mapper, reader);
		}catch(IndexOutOfBoundsException e){
		}
		return i;
	}

}
