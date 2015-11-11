package com.easytnt.grading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.service.AbstractEntityService;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.ExamRepository;
import com.easytnt.grading.repository.TeacherRepository;
import com.easytnt.grading.service.ExamService;
import com.easytnt.grading.service.TeacherService;

/**
 * 科目的具体操作服务实现类
 * 
 * @author 钟水林 20151109
 *
 */
@Service
public class TeacherServiceImpl extends AbstractEntityService<Teacher, Long>
		implements TeacherService {

	private TeacherRepository teacherRepository;

	public TeacherServiceImpl() {

	}

	@Autowired
	public void setRepository(TeacherRepository repository) {
		this.teacherRepository = repository;
		super.setRepository(repository);
	}

	@Override
	public Teacher load(Long pk) {
		Teacher teacher = teacherRepository.load(pk);
		return teacher;
	}

	@Transactional(readOnly = true)
	@Override
	public void query(Query<Teacher> query) {
		// TODO Auto-generated method stub
	}

	// 重写保存创建
	@Override
	public void create(Teacher t) {
		teacherRepository.save(t);
	}

	// 重写更新
	@Override
	public void update(Teacher t) {
		teacherRepository.update(t);
	}

	// 获取所有的Teacher信息
	public List<Teacher> tlist() {
		List<Teacher> tlist = teacherRepository.tlist();
		return tlist;
	}

	// 查询返回当前最大teacheraccount
	@Override
	public String getSeq(Long subjectid) {
		String seq = teacherRepository.getSeq(subjectid);
		return seq;
	}

	// 查询返回当前最大teacheraccount
	@Override
	public String getSeqL(Long subjectid) {
		String seq = teacherRepository.getSeqL(subjectid);
		return seq;
	}

	// 修改密码
	@Override
	public void updatePass(Long teacheckid, String pass) {
		// TODO Auto-generated method stub

	}
}
