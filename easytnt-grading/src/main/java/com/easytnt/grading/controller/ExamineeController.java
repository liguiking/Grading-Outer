package com.easytnt.grading.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.cqrs.QueryBuilder;
import com.easytnt.commons.io.FileUtil;
import com.easytnt.commons.ui.MenuGroup;
import com.easytnt.commons.web.view.ModelAndViewFactory;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.service.impl.ListDataMapperImpl;


/** 
 * <pre>
 * 
 * </pre>
 *  
 * @author 李贵庆2015年11月10日
 * @version 1.0
 **/
@Controller
@RequestMapping(value="/examinee")
public class ExamineeController {
	private static Logger logger = LoggerFactory.getLogger(ExamineeController.class);
	
	@Value("${easytnt.img.sample.dir}")
	private String imgDir;
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onGet()throws Exception {
		logger.debug("URL /examinee Method GET ");
		MenuGroup topRightMenuGroup = MenuGroupFactory.getInstance().getTopRightMenuGroup();
		MenuGroup rightMenuGroup = MenuGroupFactory.getInstance().getRightMenuGroup();
		MenuGroup configMenuGroup = MenuGroupFactory.getInstance().getConfigMenuGroup();
		configMenuGroup.activedMenuByIndex(2);
		rightMenuGroup.activedMenuByIndex(3);
		Query<Teacher> query = new QueryBuilder().newQuery(1,10,new HashMap());
		query.rows(202);
		return ModelAndViewFactory.newModelAndViewFor("/config")
				.with("menus2", topRightMenuGroup.getMenus())
				.with("rightSideMenu", rightMenuGroup.getMenus())
				.with("menus3", configMenuGroup.getMenus())
				.with("query",query)
				.with("page","examinee").build();
	}
	
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public ModelAndView onUpload(MultipartHttpServletRequest request)throws Exception {
		logger.debug("URL /upload Method POST ");
		Iterator<String> it = request.getFileNames();
		String[] titleList=null;
		if(it.hasNext()) {
			String fileName = it.next();
			MultipartFile mfile = request.getFile(fileName);
			ListDataMapperImpl ListDataMapperImpl = new ListDataMapperImpl(mfile.getInputStream(),null);
			titleList = ListDataMapperImpl.getTitleList();
			File file = FileUtil.inputStreamToFile(mfile.getInputStream(),mfile.getOriginalFilename());
			logger.debug(file.getAbsolutePath());
			if(imgDir!=null){
				File tempFile = new File(imgDir);
				tempFile.delete();
			}
			imgDir = file.getAbsolutePath();
		}else {
			throw new IllegalArgumentException("无效的文件名");
		}
		
		return ModelAndViewFactory.newModelAndViewFor().with("title",titleList).build();
	}
	
	@RequestMapping(value="/importExaminee",method = RequestMethod.POST)
	public ModelAndView importExaminee(@RequestBody Map<String,String> map)throws Exception {
		logger.debug("URL /importExaminee Method POST ");
		File file = new File(imgDir);
		file.delete();
		return ModelAndViewFactory.newModelAndViewFor().build();
	}
}
