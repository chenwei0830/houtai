package com.thinkgem.jeesite.modules.api.entity;

import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 艺术家
 */
public class ArtVo {
	
	private String id;
	private String nickName;
	private String photo;
	private String remarks;
	private String artLevel;
	private String artType;
	private int artWorksNum = 60;		//作品数
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getArtLevel() {
		return artLevel;
	}
	public void setArtLevel(String artLevel) {
		this.artLevel = artLevel;
	}
	public String getArtType() {
		String type = "";
		if(StringUtils.isNotBlank(artType)) {
			switch (artType) {
			case "0":
				type = "摄影";
				break;
			case "1":
				type = "美术";
				break;
			case "2":
				type = "书法";
				break;
			case "3":
				type = "文学";
				break;
			case "4":
				type = "舞蹈";
				break;
			case "5":
				type = "音乐";
				break;
			case "6":
				type = "戏剧";
				break;	
			default:
				type = "未知";
				break;
			}
		}
		return type;
	}
	public void setArtType(String artType) {
		this.artType = artType;
	}
	public int getArtWorksNum() {
		return artWorksNum;
	}
	public void setArtWorksNum(int artWorksNum) {
		this.artWorksNum = artWorksNum;
	}
}
