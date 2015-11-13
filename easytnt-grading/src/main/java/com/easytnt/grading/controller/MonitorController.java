package com.easytnt.grading.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easytnt.commons.ui.MenuGroup;
import com.easytnt.commons.web.view.ModelAndViewFactory;
import com.easytnt.grading.domain.ichart.Data;
import com.easytnt.grading.domain.ichart.DataList;
import com.easytnt.grading.domain.ichart.ResultData;


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
	 Random random = new Random(1);
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
		//小组一致性 
		logger.debug("URL /monitor/team Method Get");
		return createModelAndView(2,"team");
	}
	@RequestMapping(value="/monitor/teamShow/{info}",method=RequestMethod.POST)
	public @ResponseBody Object monitorTeam(String info)throws Exception{
		logger.debug("URL /monitor/{info} Method post");
		ResultData resultData = new ResultData();
		List<DataList> dataList = new ArrayList<DataList>();
		List<String> labels = new ArrayList<String>();
		List<Float> values = new ArrayList<Float>();
		DataList DataList = new DataList();
		DataList.setName("张老师");
		DataList.setLine_width(2);
		DataList.setColor("#01acb6");
		
		for(int i=1;i<=24;i++){
			values.add(((int)(random.nextFloat()*10))/10f);
			labels.add(i+"");
		}
		DataList.setValue(values);
		
		dataList.add(DataList);
		resultData.setData(dataList);
		resultData.setLabels(labels);
		resultData.setMax(1f);
		resultData.setMin(0f);
		resultData.setSpace(0.1f);
		resultData.setUnit("数据");
		return resultData;
	}
	@RequestMapping(value="/monitor/personalStabled",method=RequestMethod.GET)
	public ModelAndView onPersonalStabled()throws Exception{
		//自身稳定性
		logger.debug("URL /monitor/personalStabled Method Get");
		return createModelAndView(3,"personalStabled");
	}
	@RequestMapping(value="/monitor/personalStabledShow/{info}",method=RequestMethod.POST)
	public @ResponseBody Object monitorPersonalStabled(String info)throws Exception{
		logger.debug("URL /monitor/{info} Method post");
		ResultData resultData = new ResultData();
		List<DataList> dataListList = new ArrayList<DataList>();
		List<String> labels = new ArrayList<String>();
		String[] color = new String[]{"#a5c2d5","#cbab4f","#76a871","#76a871","#a56f8f","#c12c44","#a56f8f","#9f7961","#76a871","#6f83a5"};
		for(int index=0;index<10;index++){
			List<Float> values = new ArrayList<Float>();
			DataList dataList = new DataList();
			dataList.setName("张老师"+index);
			dataList.setLine_width(2);
			dataList.setColor(color[index]);
			
			for(int i=1;i<=24;i++){
				values.add(((int)(random.nextFloat()*10))/10f);
			}
			dataList.setValue(values);
			dataListList.add(dataList);
		}
		for(int i=1;i<=24;i++){
			labels.add(i+"");
		}
		resultData.setData(dataListList);
		resultData.setLabels(labels);
		resultData.setMax(1f);
		resultData.setMin(0f);
		resultData.setSpace(0.1f);
		resultData.setUnit("数据");
		return resultData;
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
	
	@RequestMapping(value="/monitor/{info}",method=RequestMethod.POST)
	public @ResponseBody Object getData(String info)throws Exception{
		logger.debug("URL /monitor/{info} Method post");
		Data data = new Data();
		data.setValue("2");
		data.setName("H");
		data.setColor("#ffff00");
		Data data2 = new Data();
		data2.setValue("3");
		data2.setName("H2");
		data2.setColor("#ffff00");
		List<Data> dataList = new ArrayList<Data>();
		dataList.add(data);
		dataList.add(data2);
		return dataList;
	}
}
