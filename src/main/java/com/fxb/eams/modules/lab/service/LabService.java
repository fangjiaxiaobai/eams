/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.lab.entity.Lab;
import com.fxb.eams.modules.lab.dao.LabDao;

/**
 * 实验室/教室/办公室Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class LabService extends CrudService<LabDao, Lab> {

	public Lab get(String id) {
		return super.get(id);
	}
	
	public List<Lab> findList(Lab lab) {
		return super.findList(lab);
	}
	
	public Page<Lab> findPage(Page<Lab> page, Lab lab) {
		return super.findPage(page, lab);
	}
	
	@Transactional(readOnly = false)
	public void save(Lab lab) {
		super.save(lab);
	}
	
	@Transactional(readOnly = false)
	public void delete(Lab lab) {
		super.delete(lab);
	}

    public List<Lab> getLabByNameOrLabId(Lab lab){
	    return dao.getLabByNameOrLabId(lab);
    }
}