package com.thinkgem.jeesite.modules.art.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 艺术作品Entity
 */
public class ArtWorks extends DataEntity<ArtWorks>{

	private static final long serialVersionUID = 1L;
	
	private String title; 		// 标题
	private String modelType; 	// 作品展示模型 '0'-图文 '1'-视频+文  '2'-纯文
	private String artType; 	// 艺术类别
	private User user; 			// 作者ID
	private String status;		//审核状态 '0'-待审核  '1'-审核通过 '2'-审核不通过
	private Date beginDate;		//开始日期
	private Date endDate;		//截止日期
	private String location;	//定位
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getArtType() {
		return artType;
	}
	public void setArtType(String artType) {
		this.artType = artType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
