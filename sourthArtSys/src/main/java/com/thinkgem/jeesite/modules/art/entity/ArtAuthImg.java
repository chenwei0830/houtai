package com.thinkgem.jeesite.modules.art.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 艺术认证图片Entity
 */
public class ArtAuthImg extends DataEntity<ArtAuthImg>{

	private static final long serialVersionUID = 1L;

	private String userAuthId;
	private String imgUrl;
	
	
	public String getUserAuthId() {
		return userAuthId;
	}
	public void setUserAuthId(String userAuthId) {
		this.userAuthId = userAuthId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
