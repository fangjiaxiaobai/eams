/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.device.entity.Memory;
import com.fxb.eams.modules.device.dao.MemoryDao;

/**
 * 内存信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class MemoryService extends CrudService<MemoryDao, Memory> {

	public Memory get(String id) {
		return super.get(id);
	}
	
	public List<Memory> findList(Memory memory) {
		return super.findList(memory);
	}
	
	public Page<Memory> findPage(Page<Memory> page, Memory memory) {
		return super.findPage(page, memory);
	}
	
	@Transactional(readOnly = false)
	public void save(Memory memory) {
		super.save(memory);
	}
	
	@Transactional(readOnly = false)
	public void delete(Memory memory) {
		super.delete(memory);
	}
	
}