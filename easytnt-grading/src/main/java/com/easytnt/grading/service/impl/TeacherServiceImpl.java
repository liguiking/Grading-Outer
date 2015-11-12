package com.easytnt.grading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.service.AbstractEntityService;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.ExamRepository;
import com.easytnt.grading.repository.SubjectRepository;
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
	
	@Autowired
	private SubjectRepository subjectRepository;

	public TeacherServiceImpl() {

	}

	@Autowired
	public void setRepository(TeacherRepository repository) {
		this.teacherRepository = repository;
		super.setRepository(repository);
	}

	// 修改密码
	@Override
	public void updatePass(Long teacheckid, String pass) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void create(Teacher teacher, int amount) {
		String seq = "1";
		//Subject subject = subjectRepository.load(teacher.getSubject().getId());
		//teacher.setSubject(subject);
		
		if(teacher.isManager()){
			seq = teacherRepository.getSeqL(teacher.getSubject().getId());
		}else {
			seq = teacherRepository.getSeq(teacher.getSubject().getId());
		}
		
		teacher.genAccount(Integer.valueOf(seq));

		List<Teacher> teachers = teacher.cloneTimes(amount);
		for(Teacher t:teachers) {
			this.create(t);
		}
	}
}
