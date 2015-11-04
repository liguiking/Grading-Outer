package com.easytnt.grading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.repository.SubjectRepository;
import com.easytnt.grading.service.SubjectService;
/**
 * 科目的具体操作服务实现类
 * 
 * @author 钟水林 20151103
 *
 */
@Service
public class SubjectServiceImpl implements SubjectService {

	private SubjectRepository subjectrepository;
	
	public SubjectRepository getSubjectrepository() {
		return subjectrepository;
	}

	//将dao层注入服务层
	@Autowired
	public void setSubjectrepository(SubjectRepository subjectrepository) {
		this.subjectrepository = subjectrepository;
	}

	/***************************** 定义接口实现 **************************************/
	// 科目新增
	@Override
	public void insertSubser() {
		// TODO Auto-generated method stub

	}

	// 科目删除
	@Override
	public void deleteSubser() {
		// TODO Auto-generated method stub

	}

	// 科目修改
	@Override
	public void updateSubser() {
		// TODO Auto-generated method stub

	}

	// 获取科目
	@Override
	public List<Subject> getSubser() {
		// TODO Auto-generated method stub
		return null;
	}

	/************************* 系统生成方法 ***********************************/
	@Override
	public Subject load(Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Subject t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Subject t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Subject t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Subject> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void query(Query<Subject> query) {
		// TODO Auto-generated method stub

	}
}
