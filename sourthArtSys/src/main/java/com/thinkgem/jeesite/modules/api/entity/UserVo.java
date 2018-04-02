package com.thinkgem.jeesite.modules.api.entity;

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
		return artType;
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
