package com.easytnt.grading.repository;
import com.easytnt.commons.entity.repository.Repository;
import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

public interface ExamineeRepository extends Repository{
	int imports(ListDataMapper mapper,ListDataSourceReader reader);
}