/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.teacher.dao;

import com.fxb.eams.common.persistence.CrudDao;
import com.fxb.eams.common.persistence.annotation.MyBatisDao;
import com.fxb.eams.modules.teacher.entity.Teachers;

/**
 * 教室负责人信息DAO接口
 * @author 方小白
 * @version 2017-08-22
 */
@MyBatisDao
public interface TeachersDao extends CrudDao<Teachers> {
	
}