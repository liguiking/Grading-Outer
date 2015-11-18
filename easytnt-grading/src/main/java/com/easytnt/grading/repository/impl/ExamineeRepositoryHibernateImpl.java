package com.easytnt.grading.repository.impl;

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
	public int insertImports(ListDataMapper mapper, ListDataSourceReader reader) {
		// TODO Auto-generated method stub
		new ExamineeDataImpoirtor(this.getCurrentSession(),mapper,reader).doImport();
		return 0;
	}
	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
