package com.easytnt.grading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.service.AbstractEntityService;
import com.easytnt.grading.domain.paper.ExamPaper;
import com.easytnt.grading.repository.ExamPaperRepository;
import com.easytnt.grading.service.ExamPaperService;

@Service
public class ExamPaperServiceImpl extends AbstractEntityService<ExamPaper, Long>implements ExamPaperService {

	private ExamPaperRepository examPaperRepository;
	
	public ExamPaperServiceImpl() {
		
	}
	
	@Autowired(required=false)
	public void setRepository(ExamPaperRepository  repository) {
		this.examPaperRepository = repository;
		super.setRepository(repository);
	}
	
	@Override
	public ExamPaper load(Long pk) {
		ExamPaper examPaper =  new ExamPaper();
		//exam.setName("Exam");
		return examPaper;
	}

	@Transactional(readOnly=true)
	@Override
	public void query(Query<ExamPaper> query) {
		// TODO Auto-generated method stub
	}

}
