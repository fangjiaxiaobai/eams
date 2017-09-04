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
import com.fxb.eams.modules.device.entity.CPU;
import com.fxb.eams.modules.device.service.CPUService;

/**
 * CPU信息Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/device/CPU")
public class CPUController extends BaseController {

	@Autowired
	private CPUService cPUService;
	
	@ModelAttribute
	public CPU get(@RequestParam(required=false) String id) {
		CPU entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cPUService.get(id);
		}
		if (entity == null){
			entity = new CPU();
		}
		return entity;
	}

    /***
     * 展示CPU信息，条件查询
     * @param cPU
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequiresPermissions("device:cPU:view")
	@RequestMapping(value = {"list", ""})
	public String list(CPU cPU, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CPU> page = cPUService.findPage(new Page<CPU>(request, response), cPU); 
		model.addAttribute("page", page);
		model.addAttribute("cPU",cPU);
		return "modules/device/cPUList";
	}

	@RequiresPermissions("device:cPU:view")
	@RequestMapping(value = "form")
	public String form(CPU cPU, Model model) {
		model.addAttribute("cPU", cPU);
		return "modules/device/cPUForm";
	}

    /**
     * 保存或者修改CPU信息
     * @param cPU cpu实体
     * @param model
     * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("device:cPU:edit")
	@RequestMapping(value = "save")
	public String save(CPU cPU, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cPU)){
			return form(cPU, model);
		}
		cPUService.save(cPU);
		addMessage(redirectAttributes, "保存CPU信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/cPU/?repage";
	}

    /***
     * 逻辑删除CPU信息
     * @param cPU
     * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("device:cPU:edit")
	@RequestMapping(value = "delete")
	public String delete(CPU cPU, RedirectAttributes redirectAttributes) {
		cPUService.delete(cPU);
		addMessage(redirectAttributes, "删除CPU信息成功");
		return "redirect:"+Global.getAdminPath()+"/device/cPU/?repage";
	}

}