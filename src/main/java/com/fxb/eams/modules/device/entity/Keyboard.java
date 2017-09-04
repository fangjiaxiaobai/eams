/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.entity;

import org.hibernate.validator.constraints.Length;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * 键盘信息Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class Keyboard extends DataEntity<Keyboard> {
	
	private static final long serialVersionUID = 1L;
	private String sn;		// SN
	private String deviceId;		// 设备id
	
	public Keyboard() {
		super();
	}

	public Keyboard(String id){
		super(id);
	}

	@Length(min=0, max=100, message="SN长度必须介于 0 和 100 之间")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}