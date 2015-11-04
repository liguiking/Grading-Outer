package com.easytnt.grading.repository;

import java.util.List;

import com.easytnt.commons.entity.repository.Repository;
import com.easytnt.grading.domain.exam.Subject;

/**
 * 科目的具体操作接口类
 * 
 * @author 钟水林 20151103
 *
 */
public interface SubjectRepository extends Repository<Subject, Long> {
	//科目新增
	public void insertSub();
	//科目删除
	public void deleteSub();
	//科目修改
	public void updateSub();
	//获取科目
	@SuppressWarnings("rawtypes")
	public List getSub();
}
