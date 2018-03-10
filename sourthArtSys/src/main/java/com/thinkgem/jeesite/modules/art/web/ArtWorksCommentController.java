package com.thinkgem.jeesite.modules.art.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.entity.AppJson;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksComment;
import com.thinkgem.jeesite.modules.art.service.ArtWorksCommentService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 评论管理Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/art/comment")
public class ArtWorksCommentController extends BaseController{

	@Autowired
	private ArtWorksCommentService artWorksCommentService;
	
	
	@RequiresPermissions("art:comment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ArtWorksComment artWorksComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(user!=null) {
			//机构子管理员 查看整个机构数据
			if(user.getRole()!=null && StringUtils.equals(user.getRole().getId(), Global.COM_ROLE_ADMIN_ID)) {
				artWorksComment.setOrg(user.getOrg());
			}
		}
//		if(StringUtils.isBlank(artWorksComment.getStatus())) {
//			//初始默认查询待审核的记录
//			artWorksComment.setStatus("0");
//		}
		Page<ArtWorksComment> page = artWorksCommentService.findPage(new Page<ArtWorksComment>(request, response), artWorksComment);
		model.addAttribute("page", page);
		model.addAttribute("artWorksComment", artWorksComment);
		return "modules/art/artWorksCommentList";
	}
	
	@RequiresPermissions("art:comment:edit")
	@RequestMapping(value = "form")
	public String form(ArtWorksComment artWorksComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		artWorksComment = artWorksCommentService.get(artWorksComment);
		model.addAttribute("artWorksComment", artWorksComment);
		return "modules/art/artWorksCommentForm";
	}
	
	@RequiresPermissions("art:comment:edit")
	@ResponseBody
	@RequestMapping(value = "save")
	public AppJson save(ArtWorksComment artWorksComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		artWorksCommentService.save(artWorksComment);
		return new AppJson();
	}
	
	@RequiresPermissions("art:comment:edit")
	@RequestMapping(value = "delete")
	public String delete(ArtWorksComment artWorksComment, RedirectAttributes redirectAttributes) {
		artWorksCommentService.delete(artWorksComment);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:" + adminPath + "/art/comment/?repage";
	}
	
	
	
	/**
	 * 查看作品评论
	 */
	@RequestMapping(value = {"listOfArtWorks"})
	public String listOfArtWorks(ArtWorksComment artWorksComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(user!=null) {
			//机构子管理员 查看整个机构数据
			if(user.getRole()!=null && StringUtils.equals(user.getRole().getId(), Global.COM_ROLE_ADMIN_ID)) {
				artWorksComment.setOrg(user.getOrg());
			}
		}
		Page<ArtWorksComment> page = artWorksCommentService.findPage(new Page<ArtWorksComment>(request, response), artWorksComment);
		model.addAttribute("page", page);
		model.addAttribute("artWorksComment", artWorksComment);
		return "modules/art/artCommentListOfwork";
	}
}
