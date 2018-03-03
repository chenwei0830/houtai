package com.thinkgem.jeesite.common.config;


/**
 * 微信配置
 */
public class WeXinConfig {

	//获取OPENID SESSION_KEY UNIONID
	public static final String WX_URL_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session";
	//微信小程序ID
	public static final String WX_APPID = "wx81cfb6b3dcba2164";
	//微信小程序密钥
	public static final String WX_SECRET = "f4e51844ce58c427dfdac2d46e881806";
	//填写为 authorization_code
	public static final String WX_GRANT_TYPE = "authorization_code";
}
