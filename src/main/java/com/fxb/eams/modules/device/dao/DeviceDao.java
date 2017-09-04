/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.dao;

import com.fxb.eams.common.persistence.CrudDao;
import com.fxb.eams.common.persistence.annotation.MyBatisDao;
import com.fxb.eams.modules.device.entity.Device;

/**
 * 设备信息DAO接口
 * @author 方小白
 * @version 2017-08-22
 */
@MyBatisDao
public interface DeviceDao extends CrudDao<Device> {
	
}