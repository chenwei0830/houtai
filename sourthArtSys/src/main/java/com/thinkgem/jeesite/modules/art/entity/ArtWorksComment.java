package com.thinkgem.jeesite.modules.art.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 评论Entity
 */
public class ArtWorksComment extends DataEntity<ArtWorksComment>{
	
	private static final long serialVersionUID = 1L;
	
	private User user; 			// 评论人
	private String content;		// 平评论内容
	private int dzNum;			// 点赞数
	private String status;		//审核状态 '0'-待审核  '1'-审核通过 '2'-审核不通过
	private ArtWorks artWorks;  //所属作品
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDzNum() {
		return dzNum;
	}
	public void setDzNum(int dzNum) {
		this.dzNum = dzNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArtWorks getArtWorks() {
		return artWorks;
	}
	public void setArtWorks(ArtWorks artWorks) {
		this.artWorks = artWorks;
	}
}
