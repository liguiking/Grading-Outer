package com.easytnt.grading.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.service.SubjectService;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController{
	private static Logger logger = LoggerFactory.getLogger(SubjectController.class);

	private SubjectService subjectservice;

	public SubjectService getSubjectservice() {
		return subjectservice;
	}
	//将服务层注入控制器中
	@Autowired
	public void setSubjectservice(SubjectService subjectservice) {
		this.subjectservice = subjectservice;
	}

	@SuppressWarnings("unused")
	private List<Subject> Sublist = new ArrayList<Subject>();
	
	public String getSub() {
		logger.info("获取科目");
		Sublist = subjectservice.getSubser();
		return null;
	}
}
