package com.thinkgem.jeesite.common.config;


/**
 * 微信配置
 */
public class WeXinConfig {

	//获取OPENID SESSION_KEY UNIONID
	public static final String WX_URL_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session";
	//微信小程序ID
	public static final String WX_APPID = "wxde9d9158f8ac93f5";
	//微信小程序密钥
	public static final String WX_SECRET = "2c87516e13adb6ed3e016edf6c03aa26";
	//填写为 authorization_code
	public static final String WX_GRANT_TYPE = "authorization_code";
}
