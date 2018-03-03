package com.thinkgem.jeesite.modules.api.entity;

import java.util.List;

/**
 * 艺术作品Entity
 */
public class ArtWorksVo {

	private String id;				//作品ID
	private String showModel;		//展示模式: '0'-图文 '1'-视频+文  '2'-纯文
	private String title;			//作品标题
	private String content;			//作品内容: 图文=图片URL 视频+文=视频URL 纯文=文本
	private String location;		//作品发布时定位
	private String createDate;		//创建时间
	private String monthDay;		//作品发布 日期
	private String hourMin;			//作品发布 时间
	private int msgNum;				//留言数
	private int dzNum;				//点赞数
	
	private String orgId;			//组织机构ID
	
	private UserVo author;			//作者
	private List<CommentVo> hotCommentList;	//热门评论
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowModel() {
		return showModel;
	}
	public void setShowModel(String showModel) {
		this.showModel = showModel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getMonthDay() {
		return monthDay;
	}
	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}
	public String getHourMin() {
		return hourMin;
	}
	public void setHourMin(String hourMin) {
		this.hourMin = hourMin;
	}
	public int getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}
	public int getDzNum() {
		return dzNum;
	}
	public void setDzNum(int dzNum) {
		this.dzNum = dzNum;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public UserVo getAuthor() {
		return author;
	}
	public void setAuthor(UserVo author) {
		this.author = author;
	}
	public List<CommentVo> getHotCommentList() {
		return hotCommentList;
	}
	public void setHotCommentList(List<CommentVo> hotCommentList) {
		this.hotCommentList = hotCommentList;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
