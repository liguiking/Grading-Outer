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
import com.easytnt.grading.domain.paper.ExamPaper;
import com.easytnt.grading.service.ExamPaperService;

@Controller
@RequestMapping(value = "/examPaper")
public class ExamPaperController {
	private static Logger logger = LoggerFactory.getLogger(ExamPaperController.class);

	@Autowired(required = false)
	private ExamPaperService examPaperService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onCreateExam(@RequestBody ExamPaper examPaper)
					throws Exception {
		logger.debug("URL /examPaper Method POST ", examPaper);
		examPaperService.create(examPaper);
		return ModelAndViewFactory.newModelAndViewFor("/examPaper/editExamPaper").build();
	}
	
	@RequestMapping(value = "/{examPaperId}", method = RequestMethod.GET)
	public ModelAndView onViewExam(@PathVariable Long examPaperId)
					throws Exception {
		logger.debug("URL /examPaperId/{} Method Get ", examPaperId);
		ExamPaper examPaper = examPaperService.load(examPaperId);
		return ModelAndViewFactory.newModelAndViewFor("/examPaper/editExamPaper").with("examPaper",examPaper).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView onUpdateExam(@RequestBody ExamPaper examPaper)
					throws Exception {
		logger.debug("URL /examPaper Method PUT ", examPaper);
		examPaperService.update(examPaper);
		return ModelAndViewFactory.newModelAndViewFor("/examPaper/editExamPaper").build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView onDeleteExam(@RequestBody ExamPaper examPaper)
					throws Exception {
		logger.debug("URL /examPaper Method DELETE ", examPaper);
		examPaperService.delete(examPaper);
		return ModelAndViewFactory.newModelAndViewFor().build();
	}
	
	@RequestMapping(value="/query/{page}/{size}",method = RequestMethod.GET)
	public ModelAndView onDeleteExam(@PathVariable int page,@PathVariable int size,HttpServletRequest request)
					throws Exception {
		logger.debug("URL /examPaper/query/{}/{} Method GET ", page,size);
        Query<ExamPaper> query = new QueryBuilder().newQuery(page,size,request.getParameterMap());
        examPaperService.query(query);
		return ModelAndViewFactory.newModelAndViewFor("/examPaper/listExamPaper").with("result",query.getResults())
				.with("totalPage",query.getTotalPage()).build();
	}
}
