/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.device.entity.Keyboard;
import com.fxb.eams.modules.device.dao.KeyboardDao;

/**
 * 键盘信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class KeyboardService extends CrudService<KeyboardDao, Keyboard> {

	public Keyboard get(String id) {
		return super.get(id);
	}
	
	public List<Keyboard> findList(Keyboard keyboard) {
		return super.findList(keyboard);
	}
	
	public Page<Keyboard> findPage(Page<Keyboard> page, Keyboard keyboard) {
		return super.findPage(page, keyboard);
	}
	
	@Transactional(readOnly = false)
	public void save(Keyboard keyboard) {
		super.save(keyboard);
	}
	
	@Transactional(readOnly = false)
	public void delete(Keyboard keyboard) {
		super.delete(keyboard);
	}
	
}