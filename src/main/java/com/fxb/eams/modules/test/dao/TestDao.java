/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.test.dao;

import com.fxb.eams.common.persistence.CrudDao;
import com.fxb.eams.common.persistence.annotation.MyBatisDao;
import com.fxb.eams.modules.test.entity.Test;

/**
 * 测试DAO接口
 * @author ThinkGem
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {
	
}
