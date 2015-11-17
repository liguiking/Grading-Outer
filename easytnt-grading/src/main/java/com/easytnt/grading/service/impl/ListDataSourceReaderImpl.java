package com.easytnt.grading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.easytnt.grading.repository.ExamineeRepository;
import com.easytnt.grading.service.ListDataSourceReader;

public class ListDataSourceReaderImpl implements ListDataSourceReader {
	
	@Autowired(required = false)
	private ExamineeRepository examineeRepository;
	//打开数据源
	@Override
	public void open() throws Exception {
		// TODO Auto-generated method stub
	}

	//获取某行数据
	@Override
	public String[] get(int row) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//获取某行某列数据
	@Override
	public String get(int row, int col) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//关闭数据源
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}
}
