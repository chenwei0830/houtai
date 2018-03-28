package com.thinkgem.jeesite.modules.api.entity;

/**
 * 短信验证码
 */
public class MsgCode {

	private String id;
	private String code;			//验证码内容
	private String phone;			//openId
	private String status;			//使用状态 '0'-未使用 '1'-正常使用 '-1'-作废
	
	public MsgCode() {
		
	}
	
	public MsgCode(String id,String code,String phone,String status) {
		this.id = id;
		this.code = code;
		this.phone = phone;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
