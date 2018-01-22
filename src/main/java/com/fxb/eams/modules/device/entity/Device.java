/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.device.entity;

import com.fxb.eams.modules.lab.entity.Lab;
import com.fxb.eams.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * 设备信息Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class Device extends DataEntity<Device> {
	
	private static final long serialVersionUID = 1L;
	private String brand;		// 品牌/厂商
	private String labId;		// lab_id
	private String ip;		// ip地址
	private String completed;		// 是否完整
	private Date birthday;		// 启用时间
	private String address;		// 具体位置信息
    private Lab lab;
    private User user;

    private Keyboard keyboard;
    private List<CPU> cpus;
    private List<Memory> memorys;
    private List<HardDriver> hardDrivers;

	public Device() {
		super();
	}

	public Device(String id){
		super(id);
	}

	@Length(min=0, max=255, message="品牌/厂商长度必须介于 0 和 255 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=64, message="lab_id长度必须介于 0 和 64 之间")
	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}
	
	@Length(min=0, max=100, message="ip地址长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=1, message="是否完整长度必须介于 0 和 1 之间")
	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=255, message="具体位置信息长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public void setUser(User user){
        this.user=user;
    }

    public User getUser(){
        return user;
    }

    public void setLab(Lab lab){
        this.lab=lab;
    }

    public Lab getLab(){
        return lab;
    }

    public void setKeyboard(Keyboard keyboard){
        this.keyboard=keyboard;
    }

    public Keyboard getKeyboard(){
        return keyboard;
    }

    public void setCpus(List <CPU> cpus){
        this.cpus=cpus;
    }

    public List <CPU> getCpus(){
        return cpus;
    }

    public void setHardDrivers(List <HardDriver> hardDrivers){
        this.hardDrivers=hardDrivers;
    }

    public List <HardDriver> getHardDrivers(){
        return hardDrivers;
    }

    public void setMemorys(List <Memory> memorys){
        this.memorys=memorys;
    }

    public List <Memory> getMemorys(){
        return memorys;
    }

}