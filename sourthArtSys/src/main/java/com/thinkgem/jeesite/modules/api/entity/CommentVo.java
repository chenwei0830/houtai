package com.thinkgem.jeesite.modules.api.entity;

import java.util.Date;

/**
 * 评论
 */
public class CommentVo {

	private String id;		 	//评论ID
	private String content;		//评论内容
	private String userId;		//评论人ID
	private String nickName;		//评论人名称
	private String photo;		//评论人头像
	private String artType;		//评论人艺术类别
	private String artLevel;	//评论人艺术级别
	private Date createDate;	//评论时间
	private int msgNum;			//评论留言数
	private int dzNum;			//评论点赞数
	private String msgType;		//评论类型 '1'-艺术家 '0'-普通
	
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getArtType() {
		return artType;
	}
	public void setArtType(String artType) {
		this.artType = artType;
	}
	public String getArtLevel() {
		return artLevel;
	}
	public void setArtLevel(String artLevel) {
		this.artLevel = artLevel;
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
	
}
