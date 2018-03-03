package com.thinkgem.jeesite.modules.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 接口Service
 * @author LM
 */

@Service
@Transactional(readOnly = true)
public class AppService {

	@Autowired
	private UserDao userDao;
	/**
	 * 根据艺术类别获取列表
	 */
	public List<ArtWorksVo> listArtWorks(){
		
		return null;
	}
	
	/**
	 * 根据unionId获取用户
	 */
	public User getUserByUnionId(String unionId) {
		return userDao.getByUnionId(unionId);
	}
	
	
	/**
	 * 根据unionId更新用户
	 */
	public int updateUserByUnionId(WxUser wxUser) {
		return userDao.updateUserByUnionId(wxUser);
	}
	
	
	/**
	 * 注册
	 */
	@Transactional(readOnly = false)
	public int register(WxUser wxUser) {
		User u = new User();
		u.setUnionId(wxUser.getUnionId());
		u.setNickName(wxUser.getNickName());
		u.setPhoto(wxUser.getAvatarUrl());
		u.setOrg(new Org(wxUser.getOrgId()));
		u.setUserType("-1");//未认证用户
		u.setId(IdGen.uuid());
		u.setUpdateDate(new Date());
		u.setCreateDate(new Date());
		return userDao.insert(u);
	}
}
