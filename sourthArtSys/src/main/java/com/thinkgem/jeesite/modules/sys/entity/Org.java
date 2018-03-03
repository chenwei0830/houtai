package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 组织结构Entity
 * @author LM
 */
public class Org extends DataEntity<Org>{

	private static final long serialVersionUID = 1L;
	private String orgName;			//机构名称
	private String orgCode;			//机构编码
	private String orgInfo;			//机构信息
	
	public Org() {
		super();
	}
	
	public Org(String id) {
		this.id = id;
	}
	
	public String getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(String orgInfo) {
		this.orgInfo = orgInfo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
