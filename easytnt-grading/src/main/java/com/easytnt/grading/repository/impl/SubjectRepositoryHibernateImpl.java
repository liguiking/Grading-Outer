package com.easytnt.grading.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.repository.SubjectRepository;
/**
 * 科目的具体操作实现接口类
 * 
 * @author 钟水林 20151103
 *
 */
@Repository
public class SubjectRepositoryHibernateImpl extends HibernateRepository<Subject,Long> implements SubjectRepository {
	
	
	/*****************************定义接口实现**************************************/
	//科目新增
	@Override
	public void insertSub() {
		// TODO Auto-generated method stub

	}
	//科目删除
	@Override
	public void deleteSub() {
		// TODO Auto-generated method stub

	}
	//科目修改
	@Override
	public void updateSub() {
		// TODO Auto-generated method stub

	}
	//获取科目信息
	@Override
	public List getSub() {
		// TODO Auto-generated method stub
		return null;
	}

	/*************************系统生成方法***********************************/
	
	@Override
	public void save(Subject t) {
		// TODO Auto-generated method stub

	}


	@Override
	public void saveOrUpdate(Subject t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Subject t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Subject load(Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject get(Long pk) {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
