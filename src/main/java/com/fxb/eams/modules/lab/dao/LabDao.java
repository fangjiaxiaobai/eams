/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fxb.eams.modules.lab.dao;

import com.fxb.eams.common.persistence.CrudDao;
import com.fxb.eams.common.persistence.annotation.MyBatisDao;
import com.fxb.eams.modules.lab.entity.Lab;

import java.util.List;

/**
 * 实验室/教室/办公室DAO接口
 * @author 方小白
 * @version 2017-08-22
 */
@MyBatisDao
public interface LabDao extends CrudDao<Lab> {

    List<Lab> getLabByNameOrLabId(Lab lab);
}