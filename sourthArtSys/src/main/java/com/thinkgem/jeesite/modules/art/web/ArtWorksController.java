package com.thinkgem.jeesite.modules.art.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;
import com.thinkgem.jeesite.modules.art.service.ArtWorksService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 艺术作品Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/art/artworks")
public class ArtWorksController extends BaseController{
	
	@Autowired
	private ArtWorksService artWorksService;
	
	
	@RequiresPermissions("art:artworks:view")
	@RequestMapping(value = {"list", ""})
	public String list(ArtWorks artWorks, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(user!=null) {
			//机构子管理员 查看整个机构数据
			if(user.getRole()!=null && StringUtils.equals(user.getRole().getId(), Global.COM_ROLE_ADMIN_ID)) {
				artWorks.setOrg(user.getOrg());
			}else if(user.getRole()!=null && !StringUtils.equals(user.getRole().getId(), Global.SYS_ROLE_ADMIN_ID)) {
				//非管理员查看个人数据
				artWorks.setUser(new User(user.getId()));
				//个人数据，去掉页面的搜索条件
				artWorks.setBePersonal(true);
			}
		}
		Page<ArtWorks> page = artWorksService.findPage(new Page<ArtWorks>(request, response), artWorks);
		model.addAttribute("page", page);
		model.addAttribute("artWorks", artWorks);
		return "modules/art/artWorksList";
	}

	@RequiresPermissions("art:artworks:edit")
	@RequestMapping(value = "form")
	public String form(ArtWorks artWorks, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("artWorks", artWorks);
		return "modules/art/artWorksForm";
	}
	
	@RequiresPermissions("art:artworks:view")
	@RequestMapping(value = "view")
	public String preView(ArtWorks artWorks, HttpServletRequest request, HttpServletResponse response, Model model) {
		artWorks = artWorksService.get(artWorks);
		
		if(!artWorks.getContentList().isEmpty()) {
			
			List<String> imgList = new ArrayList<String>();
			List<String> videoList = new ArrayList<String>();
			for(ArtWorksContent c : artWorks.getContentList()) {
				if(StringUtils.equals(c.getFileType(), Global.FILE_TYPE_IMG)) {
					imgList.add(c.getContent());
				}else if(StringUtils.equals(c.getFileType(), Global.FILE_TYPE_VEDIO)) {
					videoList.add(c.getContent());
				}else if(StringUtils.equals(c.getFileType(), Global.FILE_TYPE_TEXT)) {
					artWorks.setTextContent(c.getContent());
				}else {}
				
			}
			artWorks.setImgList(imgList);
			artWorks.setVideoList(videoList);
		}
		
		model.addAttribute("artWorks", artWorks);
		return "modules/art/artWorksView";
	}
	
	/**
	 * 审核提交
	 */
	@RequiresPermissions("art:auth:edit")
	@RequestMapping(value = "save")
	public String save(ArtWorks artWorks, Model model, RedirectAttributes redirectAttributes) {
		
		artWorksService.save(artWorks);
		addMessage(redirectAttributes, "审核成功");
		return "redirect:" + adminPath + "/art/artworks/?repage";
	}	
}
