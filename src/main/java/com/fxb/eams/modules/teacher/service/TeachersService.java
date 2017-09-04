/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.teacher.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.teacher.entity.Teachers;
import com.fxb.eams.modules.teacher.dao.TeachersDao;

/**
 * 教室负责人信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class TeachersService extends CrudService<TeachersDao, Teachers> {

	public Teachers get(String id) {
		return super.get(id);
	}
	
	public List<Teachers> findList(Teachers teachers) {
		return super.findList(teachers);
	}
	
	public Page<Teachers> findPage(Page<Teachers> page, Teachers teachers) {
		return super.findPage(page, teachers);
	}
	
	@Transactional(readOnly = false)
	public void save(Teachers teachers) {
		super.save(teachers);
	}
	
	@Transactional(readOnly = false)
	public void delete(Teachers teachers) {
		super.delete(teachers);
	}
	
}