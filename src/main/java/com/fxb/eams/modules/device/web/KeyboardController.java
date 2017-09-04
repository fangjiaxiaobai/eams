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
import com.fxb.eams.modules.device.entity.Keyboard;
import com.fxb.eams.modules.device.service.KeyboardService;

/**
 * 键盘信息Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/device/keyboard")
public class KeyboardController extends BaseController {

	@Autowired
	private KeyboardService keyboardService;
	
	@ModelAttribute
	public Keyboard get(@RequestParam(required=false) String id) {
		Keyboard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = keyboardService.get(id);
		}
		if (entity == null){
			entity = new Keyboard();
		}
		return entity;
	}
	
	@RequiresPermissions("device:keyboard:view")
	@RequestMapping(value = {"list", ""})
	public String list(Keyboard keyboard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Keyboard> page = keyboardService.findPage(new Page<Keyboard>(request, response), keyboard); 
		model.addAttribute("page", page);
		return "modules/device/keyboardList";
	}

	@RequiresPermissions("device:keyboard:view")
	@RequestMapping(value = "form")
	public String form(Keyboard keyboard, Model model) {
		model.addAttribute("keyboard", keyboard);
		return "modules/device/keyboardForm";
	}

	@RequiresPermissions("device:keyboard:edit")
	@RequestMapping(value = "save")
	public String save(Keyboard keyboard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, keyboard)){
			return form(keyboard, model);
		}
		keyboardService.save(keyboard);
		addMessage(redirectAttributes, "保存键盘信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/keyboard/?repage";
	}
	
	@RequiresPermissions("device:keyboard:edit")
	@RequestMapping(value = "delete")
	public String delete(Keyboard keyboard, RedirectAttributes redirectAttributes) {
		keyboardService.delete(keyboard);
		addMessage(redirectAttributes, "删除键盘信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/keyboard/?repage";
	}

}