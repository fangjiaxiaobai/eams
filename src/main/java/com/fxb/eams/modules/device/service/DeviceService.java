/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxb.eams.common.persistence.Page;
import com.fxb.eams.common.service.CrudService;
import com.fxb.eams.modules.device.entity.Device;
import com.fxb.eams.modules.device.dao.DeviceDao;

/**
 * 设备信息Service
 * @author 方小白
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class DeviceService extends CrudService<DeviceDao, Device> {

	public Device get(String id) {
		return super.get(id);
	}
	
	public List<Device> findList(Device device) {
		return super.findList(device);
	}
	
	public Page<Device> findPage(Page<Device> page, Device device) {
		return super.findPage(page, device);
	}
	
	@Transactional(readOnly = false)
	public void save(Device device) {
		super.save(device);
	}
	
	@Transactional(readOnly = false)
	public void delete(Device device) {
		super.delete(device);
	}
	
}