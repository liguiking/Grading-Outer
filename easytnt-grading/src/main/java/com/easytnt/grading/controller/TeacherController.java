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
import com.easytnt.commons.ui.MenuGroup;
import com.easytnt.commons.web.view.ModelAndViewFactory;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.service.TeacherService;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired(required = false)
	private TeacherService teacherService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onList()
					throws Exception {
		logger.debug("URL /teacher Method GET ");
		MenuGroup topRightMenuGroup = MenuGroupFactory.getInstance().getConfigMenuGroup();
		MenuGroup rightMenuGroup = MenuGroupFactory.getInstance().getRightMenuGroup();
		MenuGroup configMenuGroup = MenuGroupFactory.getInstance().getConfigMenuGroup();
		configMenuGroup.activedMenuByIndex(3);
		rightMenuGroup.activedMenuByIndex(3);
		return ModelAndViewFactory.newModelAndViewFor("/config")
				.with("result", teacherService.tlist())
				.with("menus2", topRightMenuGroup.getMenus())
				.with("rightSideMenu", rightMenuGroup.getMenus())
				.with("menus3", configMenuGroup.getMenus())
				.with("page","worker").build();
	}
	
	@RequestMapping(value="/{shu}",method = RequestMethod.POST)
	public ModelAndView onCreateTeacher(@PathVariable int shu,@RequestBody Teacher teacher)
					throws Exception {
		logger.debug("URL /Teacher Method POST ", teacher);
		if(teacher.getSubject().getName().equals("")||teacher.getSubject().getName()==null){
			teacher.getSubject().setName("自然科学");
		}
		teacher.getSubject().setName(teacher.getSubject().getName());
		//判断前台是否是生成组长账号 0-普通帐号 1为组长账号
		if(teacher.getLeader()==0){
			String seq = teacherService.getSeq(teacher.getSubject().getId());
			teacher.getSubject().setId(null);
			teacher = teacher.allocatedTo(teacher.getSubject(), Integer.parseInt(seq));
			teacherService.create(teacher);
		}else{
			for(int i=0;i<shu;i++){
				Teacher tea = new Teacher();
				String seqL = teacherService.getSeqL(teacher.getSubject().getId());
				teacher.getSubject().setId(null);
				teacher.setTeacherId(null);
				teacher.alladdleader(tea,teacher.getSubject(),Integer.parseInt(seqL));
				teacherService.create(tea);
			}
		}
		return ModelAndViewFactory.newModelAndViewFor("/teacher/editteacher").build();
	}
	
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET)
	public ModelAndView onViewTeacher(@PathVariable Long teacherId)
					throws Exception {
		logger.debug("URL /TeacherId/{} Method Get ", teacherId);
		Teacher teacher = teacherService.load(teacherId);
		return ModelAndViewFactory.newModelAndViewFor("/teacher/editTeacher").with("teacher",teacher).build();
	}
	
	//用户修改
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView onUpdateTeacher(@RequestBody Teacher teacher)
					throws Exception {
		logger.debug("URL /Teacher Method PUT ", teacher);
		teacherService.update(teacher);
		return ModelAndViewFactory.newModelAndViewFor("/teacher/editTeacher").build();
	}
	
    /*//获取所有的teacher信息
	public ModelAndView onGetTeacher(){
		logger.debug("URL /Teacher Method GET");
		List<Teacher> tlist = teacherService.tlist();
		return ModelAndViewFactory.newModelAndViewFor("/teacher/editTeacher").with("teacher", tlist).build(); 
	}
	
	//修改密码
	@RequestMapping(value ="/updatePass", method = RequestMethod.PUT)
	public ModelAndView onUpdatePass(@RequestBody Teacher teacher)
					throws Exception {
		logger.debug("URL /Teacher Method PUT ", teacher);
		teacherService.update(teacher);
		return ModelAndViewFactory.newModelAndViewFor("/teacher/editTeacher").build();
	}*/
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView onDeleteTeacher(@RequestBody Teacher teacher)
					throws Exception {
		logger.debug("URL /Teacher Method DELETE ", teacher);
		teacherService.delete(teacher);
		return ModelAndViewFactory.newModelAndViewFor().build();
	}
	
	@RequestMapping(value="/query/{page}/{size}",method = RequestMethod.GET)
	public ModelAndView onQueryTeacher(@PathVariable int page,@PathVariable int size,HttpServletRequest request)
					throws Exception {
		logger.debug("URL /Teacher/query/{}/{} Method GET ", page,size);
        Query<Teacher> query = new QueryBuilder().newQuery(page,size,request.getParameterMap());
        teacherService.query(query);
		return ModelAndViewFactory.newModelAndViewFor("/teacher/listTeacher").with("result",query.getResults())
				.with("totalPage",query.getTotalPage()).build();
	}
}
