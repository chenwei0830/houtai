package com.thinkgem.jeesite.modules.api.entity;

/**
 * 短信验证码
 */
public class MsgCode {

	private String code;			//验证码内容
	private String openId;			//openId
	private String status;			//使用状态 '0'-未使用 '1'-正常使用 '-1'-作废
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
