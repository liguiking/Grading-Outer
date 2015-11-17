package com.easytnt.grading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.easytnt.grading.repository.ExamineeRepository;
import com.easytnt.grading.service.ListDataMapper;

public class ListDataMapperImpl implements ListDataMapper {
	@Autowired(required = false)
	
	
	//读取数据源到目标数据列映射
	@Override
	public int getColIndex(String targetName) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
