package com.thinkgem.jeesite.modules.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.WeXinConfig;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.utils.QiNiuUtils;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.api.entity.WxResult;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.api.service.AppService;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

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
		
		if(wxUser!=null && StringUtils.isNotBlank(wxUser.getOpenId())) {
			//根据unionId判断是否已注册,未注册先插入数据
			User u = appService.getUserByOpenId(wxUser.getOpenId());
			if(u!=null) {
				//更新昵称+头像
				appService.updateUserByOpenId(wxUser);
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
	 * 获取openId
	 * @param jsCode 登录凭证（code）
	 */
	@ResponseBody
	@RequestMapping(value = {"getOpenId"},method = RequestMethod.GET)
	public AppJson getOpenId(@RequestParam(required=true) String jsCode) {
		
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
	
	/**
	 * 认证审核状态:返回等待审核的条数
	 */
	@ResponseBody
	@RequestMapping(value = {"authStatus"},method = RequestMethod.GET)
	public AppJson authStatus(@RequestParam String openId) {
		int a = appService.authStatus(openId);
		return new AppJson(a);
	}
	
	
	
	/**
	 * 艺术认证
	 */
	@ResponseBody
	@RequestMapping(value = {"auth"},method = RequestMethod.POST)
	public AppJson auth(@RequestBody ArtAuth artAuth) {
		
		boolean flag = appService.saveAuth(artAuth);
		if(flag) {
			return new AppJson();
		}else {
			return new AppJson("认证信息缺失或错误");
		}
	}
	

	/**
	 * 获取艺术分类及艺术级别
	 */
	@ResponseBody
	@RequestMapping(value = {"getArtTypeAndLevelList"},method = RequestMethod.GET)
	public AppJson getArtTypeAndLevelList(){
		
		//艺术分类
		List<Dict> typeList = DictUtils.getDictList("art_type");
		List<Map<String,String>> artTypeList = new ArrayList<Map<String,String>>();
		for(Dict d1 : typeList) {
			Map<String,String> m1 = new HashMap<String,String>();
			m1.put("k", d1.getValue());
			m1.put("v", d1.getLabel());
			artTypeList.add(m1);
		}
		//艺术级别
		List<Dict> levelList = DictUtils.getDictList("art_level");
		List<Map<String,String>> artLevelList = new ArrayList<Map<String,String>>();
		for(Dict d2 : levelList) {
			Map<String,String> m2 = new HashMap<String,String>();
			m2.put("k", d2.getValue());
			m2.put("v", d2.getLabel());
			artLevelList.add(m2);
		}
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("typeList", artTypeList);
		m.put("levelList", artLevelList);
		
		return new AppJson(m);
	}
	
	
	
	/**
	 * 七牛上传获取token
	 */
	@ResponseBody
	@RequestMapping(value = {"getUploadToken"},method = RequestMethod.GET)
	public Map<String,String> getUploadToken(){
		
		String token = QiNiuUtils.getUpToken();
		Map<String,String> map = new HashMap<String,String>();
		map.put("uptoken", token);
		return map;
	}
}
