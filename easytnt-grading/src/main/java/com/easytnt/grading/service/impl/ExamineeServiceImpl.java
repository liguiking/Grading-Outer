package com.easytnt.grading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public int imports(ListDataMapper mapper, ListDataSourceReader reader) {
		// TODO Auto-generated method stub
		
		
		
		
		return 0;
	}

}
