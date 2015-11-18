package com.easytnt.grading.repository.impl;

import java.util.HashMap;

import com.easytnt.grading.service.ListDataMapper;

public class ListDataMapperMocker implements ListDataMapper {
	HashMap<String,Integer> mapper = new HashMap<>();
	
	public ListDataMapperMocker() {
		mapper.put("district_name", 0);
		mapper.put("district_number", 1);
		mapper.put("school_name", 2);
		mapper.put("school_code", 3);
	}
	@Override
	public int getColIndex(String targetName) throws Exception {
		return mapper.get(targetName)+1;
	}
}
