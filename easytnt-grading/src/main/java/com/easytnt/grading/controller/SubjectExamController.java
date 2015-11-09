package com.easytnt.grading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easytnt.commons.web.view.ModelAndViewFactory;
import com.easytnt.grading.domain.exam.SubjectExam;
import com.easytnt.grading.domain.paper.ExamPaper;
import com.easytnt.grading.service.SubjectExamService;

@Controller
@RequestMapping(value = "/subjectExam")
public class SubjectExamController {
	private static Logger logger = LoggerFactory.getLogger(SubjectExamController.class);

	@Autowired(required = false)
	private SubjectExamService subjectExamService;
	
	@RequestMapping(value = "/onCreateSubjectExam",method = RequestMethod.POST)
	public ModelAndView onCreateSubjectExam(@RequestBody SubjectExam subjectExam)
					throws Exception {
		logger.debug("URL /subjectExam Method POST ", subjectExam);
		for(ExamPaper ep:subjectExam.getUsedPaper()){
			ep.addSubjectExams(subjectExam);
			subjectExam.addExamPapers(ep);
		}
		subjectExamService.create(subjectExam);
		return ModelAndViewFactory.newModelAndViewFor("/exam/editExam").build();
	}
}
