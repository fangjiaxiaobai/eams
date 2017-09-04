/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.web;

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
import com.fxb.eams.modules.device.entity.HardDriver;
import com.fxb.eams.modules.device.service.HardDriverService;

/**
 * 硬盘信息Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/device/hardDriver")
public class HardDriverController extends BaseController {

	@Autowired
	private HardDriverService hardDriverService;
	
	@ModelAttribute
	public HardDriver get(@RequestParam(required=false) String id) {
		HardDriver entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hardDriverService.get(id);
		}
		if (entity == null){
			entity = new HardDriver();
		}
		return entity;
	}
	
	@RequiresPermissions("device:hardDriver:view")
	@RequestMapping(value = {"list", ""})
	public String list(HardDriver hardDriver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HardDriver> page = hardDriverService.findPage(new Page<HardDriver>(request, response), hardDriver); 
		model.addAttribute("page", page);
		return "modules/device/hardDriverList";
	}

	@RequiresPermissions("device:hardDriver:view")
	@RequestMapping(value = "form")
	public String form(HardDriver hardDriver, Model model) {
		model.addAttribute("hardDriver", hardDriver);
		return "modules/device/hardDriverForm";
	}

	@RequiresPermissions("device:hardDriver:edit")
	@RequestMapping(value = "save")
	public String save(HardDriver hardDriver, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hardDriver)){
			return form(hardDriver, model);
		}
		hardDriverService.save(hardDriver);
		addMessage(redirectAttributes, "保存硬盘信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/hardDriver/?repage";
	}
	
	@RequiresPermissions("device:hardDriver:edit")
	@RequestMapping(value = "delete")
	public String delete(HardDriver hardDriver, RedirectAttributes redirectAttributes) {
		hardDriverService.delete(hardDriver);
		addMessage(redirectAttributes, "删除硬盘信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/hardDriver/?repage";
	}

}