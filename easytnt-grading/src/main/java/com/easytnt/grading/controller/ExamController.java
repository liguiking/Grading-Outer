package com.easytnt.grading.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.cqrs.QueryBuilder;
import com.easytnt.commons.web.view.ModelAndViewFactory;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.service.ExamService;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {
	private static Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired(required = false)
	private ExamService examService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onCreateExam(@RequestBody Exam exam)
					throws Exception {
		logger.debug("URL /exam Method POST ", exam);
		examService.create(exam);
		return ModelAndViewFactory.newModelAndViewFor("/exam/editExam").build();
	}
	
	@RequestMapping(value = "/{examId}", method = RequestMethod.GET)
	public ModelAndView onViewExam(@PathVariable Long examId)
					throws Exception {
		logger.debug("URL /examId/{} Method Get ", examId);
		Exam exam = examService.load(examId);
		return ModelAndViewFactory.newModelAndViewFor("/exam/editExam").with("exam",exam).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView onUpdateExam(@RequestBody Exam exam)
					throws Exception {
		logger.debug("URL /exam Method PUT ", exam);
		examService.update(exam);
		return ModelAndViewFactory.newModelAndViewFor("/exam/editExam").build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView onDeleteExam(@RequestBody Exam exam)
					throws Exception {
		logger.debug("URL /exam Method DELETE ", exam);
		examService.delete(exam);
		return ModelAndViewFactory.newModelAndViewFor().build();
	}
	
	@RequestMapping(value="/query/{page}/{size}",method = RequestMethod.GET)
	public ModelAndView onQueryExam(@PathVariable int page,@PathVariable int size,HttpServletRequest request)
					throws Exception {
		logger.debug("URL /exam/query/{}/{} Method GET ", page,size);
        Query<Exam> query = new QueryBuilder().newQuery(page,size,request.getParameterMap());
        examService.query(query);
		return ModelAndViewFactory.newModelAndViewFor("/exam/listExam").with("result",query.getResults())
				.with("totalPage",query.getTotalPage()).build();
	}
}
