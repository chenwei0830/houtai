package com.thinkgem.jeesite.modules.api.entity;


/**
 * API接口调用返回实例
 */
public class AppJson {
	
	/**
	 * 状态码CODE说明
	 * 0-成功
	 * 100-请求错误
	 * 101-缺少组织机构标识orgId
	 * 102-缺少参数
	 * 200-服务器出错
	 * 201-服务不可用
	 * 202-服务器正在重启
	 */
	private String code;			//状态码，0表示成功，非0表示各种不同的错误
	private String msg;
	private Object data;
	
	/**
	 * 构造函数,默认成功,data为空
	 */
	public AppJson() {
		super();
		this.code = "0";
		this.msg = "SUCCESS";
		this.data = "";
	}
	
	/**
	 * 构造函数,失败,data为空
	 */
	public AppJson(String msg) {
		super();
		this.code = "-1";
		this.msg = "FAIL";
		this.data = "";
	}
	
	/**
	 * 成功主动封装
	 */
	public AppJson(Object obj) {
		super();
		this.code = "0";
		this.msg = "SUCCESS";
		this.data = obj;
	}
	
	/**
	 * 通用自定义
	 */
	public AppJson(String code,String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = obj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
