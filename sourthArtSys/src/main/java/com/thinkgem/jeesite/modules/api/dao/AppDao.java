package com.thinkgem.jeesite.modules.api.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.entity.MsgCode;

/**
 * 接口DAO
 * @author LM
 */
@MyBatisDao
public interface AppDao {

	void toVoidMsgCode(@Param("phone")String phone);
	
	int insertMsgCode(MsgCode msgCode);
}
