package com.thinkgem.jeesite.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信工具类 SMS短信通
 * 基于 HTTPCLIENT 4.5版本的 HTTP工具类
 * @author LM
 */
public class SendMsgUtils {

	private static String SMS_UID = "lippershey1";
	private static String SMS_API_SECRET = "eff662496a457961a131";
	private static String SMS_URL_UTF8 = "http://utf8.api.smschinese.cn/";
//	private static String SMS_URL_GBK = "http://gbk.api.smschinese.cn/";
	
	
	/**
	 * 短信验证码
	 */
	public static String smsAuthCode(String smsMob,String smsText) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("Uid", SMS_UID);
		param.put("Key", SMS_API_SECRET);
		param.put("smsMob", smsMob);
		param.put("smsText", "验证码："+smsText);
		String result = HttpClientUtil.doPost(SMS_URL_UTF8, param);
		return result;
	}
	
	
	/**
	 * 生产4位随机数
	 */
	public static String randomDigits4() {
		return (int)((Math.random()*9+1)*1000)+"";
	}
	
	public static String smsAuthCodeBatch(String[] smsMobArr,String smsText) {
		
		return null;
	}
	
	
	public static void main(String[] args) {
		
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("Uid", SMS_UID);
//		param.put("Key", SMS_API_SECRET);
//		param.put("smsMob", "18284596155");
//		param.put("smsText", "验证码：6666");
//		String result = HttpClientUtil.doPost("http://utf8.api.smschinese.cn/", param);
//		System.out.println(result);
		
		String code = randomDigits4();
		System.out.println(code);
	}
}
