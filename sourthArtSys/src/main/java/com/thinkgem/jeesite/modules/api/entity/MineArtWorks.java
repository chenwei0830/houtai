package com.thinkgem.jeesite.modules.api.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 我的作品列表
 * 
 */
public class MineArtWorks {

	private String id;				//作品ID
	private String modelType;		//展示模式: '0'-图文 '1'-视频+文  '2'-纯文
	private String title;			//作品标题
	private String status;			//审核状态 0-待审 1-通过 2-拒绝
	private String mediaUrl;		//多媒体(图片、视频)URL
	private String content;			//作品内容: 图文=图片URL 视频+文=视频URL 纯文=文本
	private String location;		//作品发布时定位
	private Date createDate;		//创建时间
	private int plNum;				//留言数
	private int dzNum;				//点赞数
	private String openId;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getPlNum() {
		return plNum;
	}
	public void setPlNum(int plNum) {
		this.plNum = plNum;
	}
	public int getDzNum() {
		return dzNum;
	}
	public void setDzNum(int dzNum) {
		this.dzNum = dzNum;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
}