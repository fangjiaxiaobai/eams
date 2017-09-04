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
import com.fxb.eams.modules.device.entity.Memory;
import com.fxb.eams.modules.device.service.MemoryService;

/**
 * 内存信息Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/device/memory")
public class MemoryController extends BaseController {

	@Autowired
	private MemoryService memoryService;
	
	@ModelAttribute
	public Memory get(@RequestParam(required=false) String id) {
		Memory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memoryService.get(id);
		}
		if (entity == null){
			entity = new Memory();
		}
		return entity;
	}
	
	@RequiresPermissions("device:memory:view")
	@RequestMapping(value = {"list", ""})
	public String list(Memory memory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Memory> page = memoryService.findPage(new Page<Memory>(request, response), memory); 
		model.addAttribute("page", page);
		return "modules/device/memoryList";
	}

	@RequiresPermissions("device:memory:view")
	@RequestMapping(value = "form")
	public String form(Memory memory, Model model) {
		model.addAttribute("memory", memory);
		return "modules/device/memoryForm";
	}

	@RequiresPermissions("device:memory:edit")
	@RequestMapping(value = "save")
	public String save(Memory memory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memory)){
			return form(memory, model);
		}
		memoryService.save(memory);
		addMessage(redirectAttributes, "保存内存信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/memory/?repage";
	}
	
	@RequiresPermissions("device:memory:edit")
	@RequestMapping(value = "delete")
	public String delete(Memory memory, RedirectAttributes redirectAttributes) {
		memoryService.delete(memory);
		addMessage(redirectAttributes, "删除内存信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/memory/?repage";
	}

}