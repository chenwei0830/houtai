package com.thinkgem.jeesite.modules.api.entity;

import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 用户
 */
public class UserVo {

	private String id;
	private String nickName;
	private String photo;
	private String artType;
	private String artLevel;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getArtType() {
		
		String type = "";
		if(StringUtils.isNotBlank(artType)) {
			switch (artType) {
			case "0":
				type = "摄影";
				break;
			case "1":
				type = "美术";
				break;
			case "2":
				type = "书法";
				break;
			case "3":
				type = "文学";
				break;
			default:
				type = "未知";
				break;
			}
		}
		return type;
	}
	public void setArtType(String artType) {
		this.artType = artType;
	}
	public String getArtLevel() {
		return artLevel;
	}
	public void setArtLevel(String artLevel) {
		this.artLevel = artLevel;
	}
}
