package com.thinkgem.jeesite.modules.api.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 小程序用户信息类
 */
public class WxUser {

	private String id;
	private String nickName;		//昵称
	private String gender;			//性别
	private String language;		//语言
	private String city;			//城市
	private String province;		//省
	private String country;			//国家
	private String photo;			//头像URL
	private String openId;			//唯一标识
	private String orgId;			//机构ID
	private Date createDate;		// 创建日期
	private Date updateDate;		// 更新日期
	private String artLevel;
	private String artType;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getArtLevel() {
		return artLevel;
	}
	public void setArtLevel(String artLevel) {
		this.artLevel = artLevel;
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
}
