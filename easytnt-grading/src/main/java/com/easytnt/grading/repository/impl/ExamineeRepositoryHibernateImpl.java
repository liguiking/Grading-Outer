package com.easytnt.grading.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.repository.ExamineeRepository;
import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

@SuppressWarnings("rawtypes")
@Repository
public class ExamineeRepositoryHibernateImpl extends HibernateRepository implements ExamineeRepository {
	
	
	
	//读取数据
	@Override
	public int insertImports(JdbcTemplate jdbcTemplate,ListDataMapper mapper, ListDataSourceReader reader) throws Exception {
		new ExamineeDataImpoirtor(jdbcTemplate,this.getCurrentSession(),mapper,reader).doImport();
		return 0;
	}
	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
