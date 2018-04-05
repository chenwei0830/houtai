package com.thinkgem.jeesite.modules.api.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.QiNiuUtils;
import com.thinkgem.jeesite.common.utils.SendMsgUtils;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.api.entity.ArtWorksVo;
import com.thinkgem.jeesite.modules.api.entity.CommentVo;
import com.thinkgem.jeesite.modules.api.entity.MineArtWorks;
import com.thinkgem.jeesite.modules.api.entity.MsgCode;
import com.thinkgem.jeesite.modules.api.entity.WxResult;
import com.thinkgem.jeesite.modules.api.entity.WxUser;
import com.thinkgem.jeesite.modules.api.service.AppService;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksCollect;
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
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppService appService;
	
	/**
	 * 登录(首次登录即先完成注册再登录)
	 */
	@ResponseBody
	@RequestMapping(value = {"login"},method = RequestMethod.POST)
	public AppJson login(@RequestBody WxUser wxUser) {
		
		if(wxUser!=null && StringUtils.isNotBlank(wxUser.getOpenId()) 
				&& StringUtils.isNotBlank(wxUser.getOrgId())) {
			//根据openId判断是否已注册,未注册先插入数据
			User u = appService.getUserByOpenId(wxUser.getOpenId(),wxUser.getOrgId());
			if(u!=null) {
				//更新昵称+头像
				wxUser.setUpdateDate(new Date());
				appService.updateUserByOpenId(wxUser);
				return new AppJson("0","登录成功",null);
			}else {
				int a = appService.registerXcx(wxUser);
				if(a>0) {
					return new AppJson("0","注册-登录成功",null);
				}else {
					return new AppJson("-1","注册失败-未登录",null);
				}
			}
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
	 * 个人中心 获取作品数、收藏、关注
	 */
	@ResponseBody
	@RequestMapping(value = {"mine"},method = RequestMethod.GET)
	public AppJson saveArtworks(@RequestParam String openId) {
		
		return new AppJson(appService.getMineCountInfo(openId));
	}
	
	/**
	 * 个人中心-我的作品列表
	 */
	@ResponseBody
	@RequestMapping(value = {"mine/artworks/list"},method = RequestMethod.GET)
	public AppJson mineArtWorksList(MineArtWorks mineArtWorks) {
		
		return new AppJson(appService.getMineArtWorksList(mineArtWorks));
	}
	
	
	
	/**
	 * 保存作品
	 */
	@ResponseBody
	@RequestMapping(value = {"saveArtworks"},method = RequestMethod.POST)
	public AppJson saveArtworks(@RequestBody ArtWorks artWorks) {
		boolean flag = appService.saveArtworks(artWorks);
		if(flag) {
			return new AppJson();
		}else {
			return new AppJson("保存作品失败");
		}
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
	
	/**
	 * 获取短信验证码
	 */
	@ResponseBody
	@RequestMapping(value = {"getAuthCode"},method = RequestMethod.GET)
	public Map<String,String> getAuthCode(@RequestParam(required=true) String phone){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", "-1");
		//校验是否在黑名单
		//清理未使用的验证码
		appService.toVoidMsgCode(phone);
		//重新生产验证码
		String code = SendMsgUtils.randomDigits4();
		MsgCode msgCode = new MsgCode(IdGen.uuid(),code,phone,"0");
		int a = appService.insertMsgCode(msgCode);
		if(a>0) {
			String st = SendMsgUtils.smsAuthCode(phone, code);
			if(Integer.valueOf(st)>0) {
				map.put("code", code);
				logger.info(phone+"--验证码:"+code+"发送成功--"+DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			}else {
				logger.info(phone+"--验证码:"+code+"发送失败--"+DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			}
		}
		return map;
	}
	
	
	/**
	 * 根据ID获取作品详情
	 */
	@ResponseBody
	@RequestMapping(value = {"getArtWorksDetail"},method = RequestMethod.GET)
	public AppJson getArtWorksDetail(@RequestParam String id){
		ArtWorksVo artWorks = appService.geArtWorksDetailById(id);
		return new AppJson(artWorks);
	}
	
	/**
	 * 提交评论
	 */
	
	@ResponseBody
	@RequestMapping(value = {"saveComment"},method = RequestMethod.POST)
	public AppJson saveComment(@RequestBody CommentVo commentVo){
		int a = appService.saveComment(commentVo);
		if(a>0) {
			return new AppJson();
		}
		return new AppJson("-1","评论失败",null);
	}
	
	/**
	 * 收藏作品
	 */
	@ResponseBody
	@RequestMapping(value = {"collectArtworks"},method = RequestMethod.POST)
	public AppJson collectArtworks(@RequestBody ArtWorksCollect artWorksCollect) {
		boolean flag = appService.addCollectArtWorks(artWorksCollect);
		if(flag) {
			return new AppJson();
		}else {
			return new AppJson("保存作品失败");
		}
	}
}
