package com.thinkgem.jeesite.modules.api.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;

/**
 * 艺术作品Entity
 */
public class ArtWorksVo {

	private String id;
	private String title; 				// 标题
	private String modelType; 			// 作品展示模型 '0'-图文 '1'-视频+文  '2'-纯文
	private UserVo user; 				// 作者ID
	private String status;				//审核状态 '0'-待审核  '1'-审核通过 '2'-审核不通过
	private Date createDate;			//创建日期
	private String location;			//定位
	private List<ArtWorksContent> contentList;//内容详情
	private List<String> imgList;		//图片List
	private List<String> videoList;		//视频List
	private String textContent;			//文本内容
	private int plNum;					//评论数(已通过审核的)
	private int dzNum;					//点赞数
	private List<CommentVo> artCommmentList;//艺术家评论
	private List<CommentVo> commonCommmentList;//普通评论
	private List<CommentVo> commentList;//所有评论
	
	private int hasCollected;			//是否已收藏 0-否 1-是
	private int hasDz;					//是否已点赞
	
	
	public List<CommentVo> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentVo> commentList) {
		this.commentList = commentList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonFormat(pattern="yyyy/MM/dd HH:mm")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<ArtWorksContent> getContentList() {
		return contentList;
	}
	public void setContentList(List<ArtWorksContent> contentList) {
		this.contentList = contentList;
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
	public List<CommentVo> getArtCommmentList() {
		return artCommmentList;
	}
	public void setArtCommmentList(List<CommentVo> artCommmentList) {
		this.artCommmentList = artCommmentList;
	}
	public List<CommentVo> getCommonCommmentList() {
		return commonCommmentList;
	}
	public void setCommonCommmentList(List<CommentVo> commonCommmentList) {
		this.commonCommmentList = commonCommmentList;
	}
	public int getHasCollected() {
		return hasCollected;
	}
	public void setHasCollected(int hasCollected) {
		this.hasCollected = hasCollected;
	}
	public int getHasDz() {
		return hasDz;
	}
	public void setHasDz(int hasDz) {
		this.hasDz = hasDz;
	}
}
