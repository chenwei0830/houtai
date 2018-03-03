package com.thinkgem.jeesite.common.utils;

import com.qiniu.util.Auth;

public class QiNiuUtils {
	
	//设置好账号的ACCESS_KEY和SECRET_KEY
	private static final String ACCESS_KEY = "ZB5LeFm0VbqTWGNLoV6YGSqRGq0ljk38wRsTevT7";
	private static final String SECRET_KEY = "cJJRPU__CZRIiQwHk0iqiROEX4h2u9zJypKPRSWU";
	//设置上传空间
	private static final String BUCKET_NAME = "sourthartsys";
	//域名
	public static final String QN_URL = "http://p3mjvv81y.bkt.clouddn.com";
	
	//上传策略
	public static String getUpToken() {
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		return auth.uploadToken(BUCKET_NAME);
	}
	
}
