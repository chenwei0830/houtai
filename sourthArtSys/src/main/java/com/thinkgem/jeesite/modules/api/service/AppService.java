package com.thinkgem.jeesite.modules.api.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.PhoneUtils;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.art.dao.ArtAuthDao;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;
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
	
	@Autowired
	private ArtAuthDao artAuthDao;
	/**
	 * 根据艺术类别获取列表
	 */
	public List<ArtWorksVo> listArtWorks(){
		
		return null;
	}
	
	/**
	 * 根据unionId获取用户
	 */
	public User getUserByOpenId(String openId) {
		return userDao.getByOpendId(openId);
	}
	
	
	/**
	 * 根据unionId更新用户
	 */
	public int updateUserByOpenId(WxUser wxUser) {
		return userDao.updateUserByOpenId(wxUser);
	}
	
	
	/**
	 * 认证保存
	 */
	@Transactional(readOnly = false)
	public boolean saveAuth(ArtAuth artAuth) {
		
		//校验
		if(StringUtils.isNotBlank(artAuth.getOpenId())
				&& StringUtils.isNotBlank(artAuth.getName())
				&& StringUtils.isNotBlank(artAuth.getIdCard()) 
				&& IdcardUtils.validateCard(artAuth.getIdCard())
				&& StringUtils.isNotBlank(artAuth.getPhone()) 
				&& PhoneUtils.checkMobile(artAuth.getPhone())
				&& StringUtils.isNotBlank(artAuth.getArtType())
				&& StringUtils.isNotBlank(artAuth.getArtLevel())
				&& !artAuth.getrList().isEmpty()
				&& StringUtils.isNotBlank(artAuth.getOrgId())) {
			
			//插入主记录
			artAuth.preInsert();
			artAuth.setStatus("0");//强制设置为'0'-待审核状态
			artAuth.setOrg(new Org(artAuth.getOrgId()));
			int a = artAuthDao.insert(artAuth);
			if(a>0) {
				//插入认证图片
				for(String imgUrl : artAuth.getrList()) {
					ArtAuthImg img = new ArtAuthImg();
					img.setUserAuthId(artAuth.getId());
					img.setImgUrl(imgUrl);
					img.preInsert();
					artAuthDao.insertAuthImg(img);
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 查询认证审核状态
	 * @return
	 */
	public int authStatus(String openId) {
		return artAuthDao.authStatus(openId);
	}
	
	
	/**
	 * 注册
	 */
	@Transactional(readOnly = false)
	public int register(WxUser wxUser) {
		User u = new User();
		u.setOpenId(wxUser.getOpenId());
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
