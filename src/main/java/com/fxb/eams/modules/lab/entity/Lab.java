/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.lab.entity;

import com.fxb.eams.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * 实验室/教室/办公室Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class Lab extends DataEntity<Lab> {
	
	private static final long serialVersionUID = 1L;
	private String labName;		// 实验室名称
	private String labId;		// 实验室门牌号
	private String labPrincipal;		// 负责人
	private String labBandWidthToal;		// 带宽
	private int labCabinetCount;		// 机柜数量
	private Date labBirthday;		// 启用时间
	private Date labExpireday;		// 到期时间
	private int labComputerCount;		// 应有计算机数量
	private String labAddress;		// 实验室地址
	private String labType;		// 实验室类型
	private int labDeskCount;		// 应有课桌数量
	private String labStartIp;		// 起始ip
	private String labEndIp;		// 结束ip
    private User user;   //负责人
	
	public Lab() {
		super();
	}

	public Lab(String id){
		super(id);
	}

	@Length(min=1, max=32, message="实验室名称长度必须介于 1 和 32 之间")
	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	@Length(min=1, max=32, message="实验室门牌号长度必须介于 1 和 32 之间")
	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getLabPrincipal() {
		return labPrincipal;
	}

	public void setLabPrincipal(String labPrincipal) {
		this.labPrincipal = labPrincipal;
	}
	
	@Length(min=0, max=11, message="带宽长度必须介于 0 和 11 之间")
	public String getLabBandWidthToal() {
		return labBandWidthToal;
	}

	public void setLabBandWidthToal(String labBandWidthToal) {
		this.labBandWidthToal = labBandWidthToal;
	}

	public int getLabCabinetCount() {
		return labCabinetCount;
	}

	public void setLabCabinetCount(int labCabinetCount) {
		this.labCabinetCount = labCabinetCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLabBirthday() {
		return labBirthday;
	}

	public void setLabBirthday(Date labBirthday) {
		this.labBirthday = labBirthday;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLabExpireday() {
		return labExpireday;
	}

	public void setLabExpireday(Date labExpireday) {
		this.labExpireday = labExpireday;
	}

	public int getLabComputerCount() {
		return labComputerCount;
	}

	public void setLabComputerCount(int labComputerCount) {
		this.labComputerCount = labComputerCount;
	}
	
	@Length(min=0, max=255, message="实验室地址长度必须介于 0 和 255 之间")
	public String getLabAddress() {
		return labAddress;
	}

	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}
	
	@Length(min=0, max=64, message="实验室类型长度必须介于 0 和 64 之间")
	public String getLabType() {
		return labType;
	}

	public void setLabType(String labType) {
		this.labType = labType;
	}
	
	public int getLabDeskCount() {
		return labDeskCount;
	}

	public void setLabDeskCount(int labDeskCount) {
		this.labDeskCount = labDeskCount;
	}
	
	@Length(min=0, max=32, message="起始ip长度必须介于 0 和 32 之间")
	public String getLabStartIp() {
		return labStartIp;
	}

	public void setLabStartIp(String labStartIp) {
		this.labStartIp = labStartIp;
	}
	
	@Length(min=0, max=32, message="结束ip长度必须介于 0 和 32 之间")
	public String getLabEndIp() {
		return labEndIp;
	}

	public void setLabEndIp(String labEndIp) {
		this.labEndIp = labEndIp;
	}

    public void setUser(User user){
        this.user=user;
    }

    public User getUser(){
        return user;
    }
}