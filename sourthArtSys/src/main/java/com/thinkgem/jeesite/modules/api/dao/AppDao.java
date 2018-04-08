package com.thinkgem.jeesite.modules.api.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.entity.MsgCode;
import com.thinkgem.jeesite.modules.api.entity.UserDzVo;

/**
 * 接口DAO
 * @author LM
 */
@MyBatisDao
public interface AppDao {

	void toVoidMsgCode(@Param("phone")String phone);
	
	int insertMsgCode(MsgCode msgCode);
	
	int deleteArtWorksDZ(UserDzVo userDzVo);
	
	int deleteCommentDZ(UserDzVo userDzVo);
	
	int insertArtWorksDZ(UserDzVo userDzVo);
	
	int insertCommentDZ(UserDzVo userDzVo);
	
	int updateArtWorksDZ(UserDzVo userDzVo);
	
	int updateCommentDZ(UserDzVo userDzVo);
	
}
