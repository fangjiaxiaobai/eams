/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.device.entity.HardDriver;
import com.fxb.eams.modules.device.dao.HardDriverDao;

/**
 * 硬盘信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class HardDriverService extends CrudService<HardDriverDao, HardDriver> {

	public HardDriver get(String id) {
		return super.get(id);
	}
	
	public List<HardDriver> findList(HardDriver hardDriver) {
		return super.findList(hardDriver);
	}
	
	public Page<HardDriver> findPage(Page<HardDriver> page, HardDriver hardDriver) {
		return super.findPage(page, hardDriver);
	}
	
	@Transactional(readOnly = false)
	public void save(HardDriver hardDriver) {
		super.save(hardDriver);
	}
	
	@Transactional(readOnly = false)
	public void delete(HardDriver hardDriver) {
		super.delete(hardDriver);
	}
	
}