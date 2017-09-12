/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.lab.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxb.eams.common.utils.CacheUtils;
import com.fxb.eams.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fxb.eams.common.config.Global;
import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.web.BaseController;
import com.fxb.eams.common.utils.StringUtils;
import com.fxb.eams.modules.lab.entity.Lab;
import com.fxb.eams.modules.lab.service.LabService;

import java.util.List;

/**
 * 实验室/教室/办公室Controller
 * @author 方小白
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/lab/")
public class LabController extends BaseController {

	@Autowired
	private LabService labService;
	private static Logger logger =org.slf4j.LoggerFactory.getLogger(LabController.class);
	@ModelAttribute
	public Lab get(@RequestParam(required=false) String id) {
		Lab entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = labService.get(id);
		}
		if (entity == null){
			entity = new Lab();
		}
		return entity;
	}
	
	@RequiresPermissions("lab:lab:view")
	@RequestMapping(value = {"list", ""})
	public String list(Lab lab, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Lab> page = labService.findPage(new Page<Lab>(request, response), lab);
		model.addAttribute("page", page);
		return "modules/lab/labList";
	}

	@RequiresPermissions("lab:lab:view")
	@RequestMapping(value = "form")
	public String form(Lab lab, Model model) {
		model.addAttribute("lab", lab);
		return "modules/lab/labForm";
	}

	@RequiresPermissions("lab:lab:edit")
	@RequestMapping(value = "save")
	public String save(Lab lab, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lab)){
			return form(lab, model);
		}
		labService.save(lab);
		addMessage(redirectAttributes, "保存"+lab.getLabType()+"成功");
		return "redirect:"+Global.getAdminPath()+"/lab/?repage";
	}
	
	@RequiresPermissions("lab:lab:edit")
	@RequestMapping(value = "delete")
	public String delete(Lab lab, RedirectAttributes redirectAttributes) {
		labService.delete(lab);
		addMessage(redirectAttributes, "删除"+lab.getLabType()+"成功");
		return "redirect:"+Global.getAdminPath()+"/lab/?repage";
	}

	@RequestMapping("getLabByNameOrLabId")
    @ResponseBody
    public String getLabByNameOrLabId(Lab lab, HttpServletResponse response){

        JSONArray ja = new JSONArray();
        List <Lab> labs=this.labService.getLabByNameOrLabId(lab);
        for (Lab lab1 : labs) {
            JSONObject jo = new JSONObject();
            jo.put("id",lab1.getId());
            jo.put("labName",lab1.getLabName());
            jo.put("labId",lab1.getLabId());
            jo.put("text",lab1.getLabName()+"("+lab1.getLabId()+")");
            ja.put(jo);
        }

        return ja.toString();
    }

    @ResponseBody
    @RequestMapping("getLabByIdToJson")
    public String getLabByIdToJson(String id){
        Lab lab = this.get(id);
        JSONObject jo = new JSONObject();
        jo.put("labName",lab.getLabName());
        jo.put("labId",lab.getLabId());
        return jo.toString();
    }


}