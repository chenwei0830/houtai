package com.thinkgem.jeesite.modules.api.entity;

/**
 * 小程序接口水印
 */
public class WxWaterMark {

	private String timestamp;	//时间戳
	private String appid;		//appId
	
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
}
