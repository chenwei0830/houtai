package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Org;
import com.thinkgem.jeesite.modules.sys.service.OrgService;

/**
 * 组织机构Controller
 * @author LM
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/org")
public class OrgController extends BaseController{

	@Autowired
	private OrgService orgService;
	
	@RequiresPermissions("sys:org:view")
	@RequestMapping(value = {"list", ""})
	public String list(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
        model.addAttribute("page", page);
        model.addAttribute("org", org);
		return "modules/sys/orgList";
	}

	@RequiresPermissions("sys:org:edit")
	@RequestMapping(value = "form")
	public String form(Org org, Model model) {
		if(org!=null && StringUtils.isNotBlank(org.getId())) {
			org = orgService.get(org);
		}
		model.addAttribute("org", org);
		return "modules/sys/orgForm";
	}

	@RequiresPermissions("sys:org:edit")
	@RequestMapping(value = "save")
	public String save(Org org, Model model, RedirectAttributes redirectAttributes) {
		if(StringUtils.isBlank(org.getId())) {//新增，初始化orgCode
			org.setOrgCode(IdGen.uuid());
		}
		orgService.save(org);
		addMessage(redirectAttributes, "保存机构'" + org.getOrgName() + "'成功");
		return "redirect:" + adminPath + "/sys/org/?repage";
	}
	
	@RequiresPermissions("sys:org:edit")
	@RequestMapping(value = "delete")
	public String delete(Org org, RedirectAttributes redirectAttributes) {
		orgService.delete(org);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/org/?repage";
	}
}
