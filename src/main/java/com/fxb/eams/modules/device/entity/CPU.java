/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.entity;

import org.hibernate.validator.constraints.Length;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * CPU信息Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class CPU extends DataEntity<CPU> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
	private String brand;		// 品牌/厂商
	private String model;		// 型号
	private String coresNumber;		// 核心数量
	private String kernalcode;		// 核心代号
	private String interfaceType;		// 接口类型
	private String basicFrequency;		// 主频
	private String l1Cache;		// 一级缓存
	private String l2Cache;		// 二级缓存
	private String l3Cache;		// 三级缓存
	private String processTechnology;		// 制程工艺
	private String power;		// 功率
	private String support64;		// 是否支持64位
	
	public CPU() {
		super();
	}

	public CPU(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=100, message="品牌/厂商长度必须介于 0 和 100 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=32, message="核心数量长度必须介于 0 和 32 之间")
	public String getCoresNumber() {
		return coresNumber;
	}

	public void setCoresNumber(String coresNumber) {
		this.coresNumber = coresNumber;
	}
	
	@Length(min=0, max=64, message="核心代号长度必须介于 0 和 64 之间")
	public String getKernalcode() {
		return kernalcode;
	}

	public void setKernalcode(String kernalcode) {
		this.kernalcode = kernalcode;
	}
	
	@Length(min=0, max=100, message="接口类型长度必须介于 0 和 100 之间")
	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	@Length(min=0, max=100, message="主频长度必须介于 0 和 100 之间")
	public String getBasicFrequency() {
		return basicFrequency;
	}

	public void setBasicFrequency(String basicFrequency) {
		this.basicFrequency = basicFrequency;
	}
	
	@Length(min=0, max=32, message="一级缓存长度必须介于 0 和 32 之间")
	public String getL1Cache() {
		return l1Cache;
	}

	public void setL1Cache(String l1Cache) {
		this.l1Cache = l1Cache;
	}
	
	@Length(min=0, max=32, message="二级缓存长度必须介于 0 和 32 之间")
	public String getL2Cache() {
		return l2Cache;
	}

	public void setL2Cache(String l2Cache) {
		this.l2Cache = l2Cache;
	}
	
	@Length(min=0, max=32, message="三级缓存长度必须介于 0 和 32 之间")
	public String getL3Cache() {
		return l3Cache;
	}

	public void setL3Cache(String l3Cache) {
		this.l3Cache = l3Cache;
	}
	
	@Length(min=0, max=100, message="制程工艺长度必须介于 0 和 100 之间")
	public String getProcessTechnology() {
		return processTechnology;
	}

	public void setProcessTechnology(String processTechnology) {
		this.processTechnology = processTechnology;
	}
	
	@Length(min=0, max=32, message="功率长度必须介于 0 和 32 之间")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	@Length(min=0, max=1, message="是否支持64位长度必须介于 0 和 1 之间")
	public String getSupport64() {
		return support64;
	}

	public void setSupport64(String support64) {
		this.support64 = support64;
	}
	
}