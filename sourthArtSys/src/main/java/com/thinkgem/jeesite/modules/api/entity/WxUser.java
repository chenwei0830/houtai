package com.thinkgem.jeesite.modules.api.entity;


/**
 * 小程序用户信息类
 */
public class WxUser {

	private String nickName;		//昵称
	private String gender;			//性别
	private String language;		//语言
	private String city;			//城市
	private String province;		//省
	private String country;			//国家
	private String avatarUrl;		//头像URL
	private String openId;			//唯一标识
	private WxWaterMark watermark;	//水印
	private String orgId;			//机构ID
	
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
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public WxWaterMark getWatermark() {
		return watermark;
	}
	public void setWatermark(WxWaterMark watermark) {
		this.watermark = watermark;
	}
}
