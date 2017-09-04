/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.teacher.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fxb.eams.common.config.Global;
import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.web.BaseController;
import com.fxb.eams.common.utils.StringUtils;
import com.fxb.eams.modules.teacher.entity.Teachers;
import com.fxb.eams.modules.teacher.service.TeachersService;

/**
 * 教室负责人信息Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/teachers")
public class TeachersController extends BaseController {

	@Autowired
	private TeachersService teachersService;
	
	@ModelAttribute
	public Teachers get(@RequestParam(required=false) String id) {
		Teachers entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teachersService.get(id);
		}
		if (entity == null){
			entity = new Teachers();
		}
		return entity;
	}
	
	@RequiresPermissions("teacher:teachers:view")
	@RequestMapping(value = {"list", ""})
	public String list(Teachers teachers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Teachers> page = teachersService.findPage(new Page<Teachers>(request, response), teachers); 
		model.addAttribute("page", page);
		return "modules/teacher/teachersList";
	}

	@RequiresPermissions("teacher:teachers:view")
	@RequestMapping(value = "form")
	public String form(Teachers teachers, Model model) {
		model.addAttribute("teachers", teachers);
		return "modules/teacher/teachersForm";
	}

	@RequiresPermissions("teacher:teachers:edit")
	@RequestMapping(value = "save")
	public String save(Teachers teachers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teachers)){
			return form(teachers, model);
		}
		teachersService.save(teachers);
		addMessage(redirectAttributes, "保存教室负责人信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teachers/?repage";
	}
	
	@RequiresPermissions("teacher:teachers:edit")
	@RequestMapping(value = "delete")
	public String delete(Teachers teachers, RedirectAttributes redirectAttributes) {
		teachersService.delete(teachers);
		addMessage(redirectAttributes, "删除教室负责人信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teachers/?repage";
	}

}