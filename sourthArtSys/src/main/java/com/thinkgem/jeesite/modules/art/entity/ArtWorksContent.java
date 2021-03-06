package com.thinkgem.jeesite.modules.art.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 作品详情
 */
public class ArtWorksContent extends DataEntity<ArtWorksContent>{

	private static final long serialVersionUID = 1L;
	
	private String artWorksId;			//作品ID
	private String fileType;			//内容类型  0-图片 1-视频 2-文本
	private String content;				//具体内容
	private int sort;					//排序
	private String gifUrl;				//视频对应的gifUrl
	
	public String getGifUrl() {
		return gifUrl;
	}
	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getArtWorksId() {
		return artWorksId;
	}
	public void setArtWorksId(String artWorksId) {
		this.artWorksId = artWorksId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
