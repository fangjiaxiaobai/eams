/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.teacher.entity;

import org.hibernate.validator.constraints.Length;

import com.fxb.eams.common.persistence.DataEntity;

/**
 * 教室负责人信息Entity
 * @author 方小白
 * @version 2017-08-22
 */
public class Teachers extends DataEntity<Teachers> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String photo;		// 照片
	private String positionalTitle;		// 职位职称
	private String professionalTitle;		// 学历职称
	private String officeplace;		// 办公室
	
	public Teachers() {
		super();
	}

	public Teachers(String id){
		super(id);
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=6, message="性别长度必须介于 1 和 6 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=255, message="照片长度必须介于 0 和 255 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=64, message="职位职称长度必须介于 0 和 64 之间")
	public String getPositionalTitle() {
		return positionalTitle;
	}

	public void setPositionalTitle(String positionalTitle) {
		this.positionalTitle = positionalTitle;
	}
	
	@Length(min=0, max=64, message="学历职称长度必须介于 0 和 64 之间")
	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}
	
	@Length(min=0, max=64, message="办公室长度必须介于 0 和 64 之间")
	public String getOfficeplace() {
		return officeplace;
	}

	public void setOfficeplace(String officeplace) {
		this.officeplace = officeplace;
	}
	
}