package com.thinkgem.jeesite.modules.art.entity;

import java.util.Date;
import java.util.List;

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
	private List<ArtWorksContent> contentList;//内容详情
	
	private List<String> imgList;		//图片List
	private List<String> videoList;		//视频List
	private String textContent;			//文本内容
	private int plNum;					//评论数(已通过审核的)
	
	
	public int getPlNum() {
		return plNum;
	}
	public void setPlNum(int plNum) {
		this.plNum = plNum;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public List<String> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<String> videoList) {
		this.videoList = videoList;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public List<ArtWorksContent> getContentList() {
		return contentList;
	}
	public void setContentList(List<ArtWorksContent> contentList) {
		this.contentList = contentList;
	}
	
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
