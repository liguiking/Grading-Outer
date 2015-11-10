package com.easytnt.grading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easytnt.commons.ui.MenuGroup;
import com.easytnt.commons.web.view.ModelAndViewFactory;


/**
 * <pre>
 *  
 * </pre>
 * 
 * @author 李贵庆2015年11月9日
 * @version 1.0
 **/
@Controller
public class MonitorController {
	 private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
	 
	@RequestMapping(value="/monitor/progress",method=RequestMethod.GET)
	public ModelAndView onMonitorProgress()throws Exception{
		logger.debug("URL /monitor/progress Method Get");
		return createModelAndView(0,"progress");
	}
	
	@RequestMapping(value="/monitor/worker",method=RequestMethod.GET)
	public ModelAndView onMonitorWorker()throws Exception{
		logger.debug("URL /monitor/worker Method Get");
		return createModelAndView(1,"worker");
	}
	
	@RequestMapping(value="/monitor/team",method=RequestMethod.GET)
	public ModelAndView onMonitorTeam()throws Exception{
		logger.debug("URL /monitor/team Method Get");
		return createModelAndView(2,"team");
	}
	
	@RequestMapping(value="/monitor/personalStabled",method=RequestMethod.GET)
	public ModelAndView onPersonalStabled()throws Exception{
		logger.debug("URL /monitor/personalStabled Method Get");
		return createModelAndView(3,"personalStabled");
	}
	
	private ModelAndView createModelAndView(int activedIndex,String page) {
		MenuGroup topRightMenuGroup = MenuGroupFactory.getInstance().getTopRightMenuGroup();
		MenuGroup rightMenuGroup = MenuGroupFactory.getInstance().getRightMenuGroup();
		MenuGroup monitorMenuGroup = MenuGroupFactory.getInstance().getMonitorMenuGroup();
		monitorMenuGroup.activedMenuByIndex(activedIndex);
		rightMenuGroup.activedMenuByIndex(2);
		return ModelAndViewFactory.newModelAndViewFor("/monitor")
				.with("menus2", topRightMenuGroup.getMenus())
				.with("rightSideMenu", rightMenuGroup.getMenus())
				.with("menus3", monitorMenuGroup.getMenus())
				.with("page",page).build();
	}
}
