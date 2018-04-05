package com.thinkgem.jeesite.modules.art.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收藏艺术作品Entity
 */
public class ArtWorksCollect extends DataEntity<ArtWorksCollect>{

	private static final long serialVersionUID = 1L;

	private String openId;
	private String userId;
	private String artWorksId; 
	private int type; 			// 0表示取消收藏，1表示收藏
	private String orgId;		//机构ID
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getArtWorksId() {
		return artWorksId;
	}
	public void setArtWorksId(String artWorksId) {
		this.artWorksId = artWorksId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
