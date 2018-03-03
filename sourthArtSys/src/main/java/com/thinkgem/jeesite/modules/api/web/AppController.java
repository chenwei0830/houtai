package com.thinkgem.jeesite.modules.api.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.WeXinConfig;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.api.entity.WxResult;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.api.service.AppService;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 小程序接口请求控制器Controller
 * @author LM
 */
@Controller
@RequestMapping(value = "api")
public class AppController {

	@Autowired
	private AppService appService;
	
	/**
	 * 登录(首次登录即先完成注册再登录)
	 */
	@ResponseBody
	@RequestMapping(value = {"login"},method = RequestMethod.POST)
	public AppJson login(@RequestBody WxUser wxUser) {
		
		if(wxUser!=null && StringUtils.isNotBlank(wxUser.getUnionId())) {
			//根据unionId判断是否已注册,未注册先插入数据
			User u = appService.getUserByUnionId(wxUser.getUnionId());
			if(u!=null) {
				//更新昵称+头像
				appService.updateUserByUnionId(wxUser);
				return new AppJson("0","登录成功",null);
			}else {
				int a = appService.register(wxUser);
				if(a>0) {
					return new AppJson("0","注册-登录成功",null);
				}else {
					return new AppJson("-1","注册失败-未登录",null);
				}
			}
			//将数据库openId字段换成unionId
		}else {
			return new AppJson("-1","缺少必要参数",null);
		}
		
	}
	
	
	/**
	 * 艺术认证
	 */
	@ResponseBody
	@RequestMapping(value = {"auth"},method = RequestMethod.POST)
	public AppJson auth(@RequestBody WxUser wxUser) {
		
		
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * 获取openId
	 * @param jsCode 登录凭证（code）
	 */
	@ResponseBody
	@RequestMapping(value = {"getOpenId"},method = RequestMethod.GET)
	public AppJson getOpenId(String jsCode) {
		
		WxResult wxResult = new WxResult();
		if(StringUtils.isNotBlank(jsCode)) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("appid", WeXinConfig.WX_APPID);
			params.put("secret", WeXinConfig.WX_SECRET);
			params.put("js_code", jsCode);
			params.put("grant_type", WeXinConfig.WX_GRANT_TYPE);
			String result = HttpClientUtil.doPostSSL(WeXinConfig.WX_URL_JSCODE_2_SESSION, params);
			wxResult = (WxResult) JsonMapper.fromJsonString(result, WxResult.class);
		}else {
			wxResult.setErrcode("40029");
			wxResult.setErrmsg("invalid code");
		}
		return new AppJson(wxResult);
	}
	
	
	/**
	 * 1.1首页-推荐
	 * type '1'-推荐 '2'-关注 '3'-摄影 '4'-美术 '5'-书法 '6'-文学
	 * @param orgId 组织机构ID
	 */
	@ResponseBody
	@RequestMapping(value = {"v1/listHome/{artType}"},method = RequestMethod.GET)
	public AppJson listHome(@PathVariable("artType") String artType) {
		AppJson jon = null;
		//获取作品
		
		
		
		return jon;
	}
}
