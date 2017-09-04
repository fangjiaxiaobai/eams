/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.entity;

import org.hibernate.validator.constraints.Length;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * 硬盘信息Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class HardDriver extends DataEntity<HardDriver> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
	private String brand;		// 品牌/厂商
	private String capacity;		// 硬盘大小
	private String sn;		// 硬盘SN号
	
	public HardDriver() {
		super();
	}

	public HardDriver(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=255, message="品牌/厂商长度必须介于 0 和 255 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=255, message="硬盘大小长度必须介于 0 和 255 之间")
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	@Length(min=1, max=100, message="硬盘SN号长度必须介于 1 和 100 之间")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
}