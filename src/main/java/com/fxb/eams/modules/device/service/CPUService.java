/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.device.entity.CPU;
import com.fxb.eams.modules.device.dao.CPUDao;

/**
 * CPU信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class CPUService extends CrudService<CPUDao, CPU> {

	public CPU get(String id) {
		return super.get(id);
	}
	
	public List<CPU> findList(CPU cPU) {
		return super.findList(cPU);
	}
	
	public Page<CPU> findPage(Page<CPU> page, CPU cPU) {
		return super.findPage(page, cPU);
	}
	
	@Transactional(readOnly = false)
	public void save(CPU cPU) {
		super.save(cPU);
	}
	
	@Transactional(readOnly = false)
	public void delete(CPU cPU) {
		super.delete(cPU);
	}
	
}