package com.thinkgem.jeesite.modules.art.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.QiNiuUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;
import com.thinkgem.jeesite.modules.art.service.ArtAuthService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 艺术认证管理Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/art/auth")
public class ArtAuthController extends BaseController{

	@Autowired
	private ArtAuthService artAuthService;
	
	@RequiresPermissions("art:auth:view")
	@RequestMapping(value = {"list", ""})
	public String list(ArtAuth artAuth, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(user!=null) {
			//机构子管理员 查看整个机构数据
			if(user.getRole()!=null && StringUtils.equals(user.getRole().getId(), Global.COM_ROLE_ADMIN_ID)) {
				artAuth.setOrg(user.getOrg());
			}
		}
		Page<ArtAuth> page = artAuthService.findPage(new Page<ArtAuth>(request, response), artAuth);
		model.addAttribute("page", page);
		model.addAttribute("artAuth", artAuth);
		return "modules/art/artAuthList";
	}
	
	@RequiresPermissions("art:auth:edit")
	@RequestMapping(value = "form")
	public String form(ArtAuth artAuth, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if (StringUtils.isNotBlank(artAuth.getId())){
			artAuth = artAuthService.get(artAuth);
			//获取图片
			List<ArtAuthImg> imgList = artAuthService.getArtAuthImgList(artAuth);
			artAuth.setImgList(imgList);
		}
		model.addAttribute("artAuth", artAuth);
		return "modules/art/artAuthForm";
	}
	
	@RequiresPermissions("art:auth:edit")
	@RequestMapping(value = "save")
	public String save(ArtAuth artAuth, Model model, RedirectAttributes redirectAttributes) {
		
		artAuthService.save(artAuth);
		//更新文件
		if(StringUtils.isNotBlank(artAuth.getFiles())) {
			String[] fileIds = artAuth.getFiles().split(",");
			artAuthService.saveAuthImgs(fileIds,artAuth.getId());
		}
		
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + adminPath + "/art/auth/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "upload")
	public AppJson upload(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false)List<MultipartFile> files) {
		
		for(MultipartFile file : files) {
			UploadManager uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
			Response resp = null;
			try {
				resp = uploadManager.put(file.getBytes(), null, QiNiuUtils.getUpToken());
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(resp.bodyString(), DefaultPutRet.class);
				return new AppJson("200","成功",putRet.key);
			} catch (QiniuException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new AppJson("上传失败");
	}
}
