package com.thinkgem.jeesite.modules.api.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class ArtWorksIndexPage {
	private String id;				//作品ID
	private String modelType;		//展示模式: '0'-图文 '1'-视频+文  '2'-纯文
	private String nickName;		//作者昵称
	private String photo;			//作者头像
	private String artLevel;		//艺术级别
	private String artType;			//艺术类型
	private String location;		//定位
	private Date createDate;		//创建日期
	private String title;			//标题
	private String content;			//内容
	private int dzNum;				//点赞
	private int plNum;				//评论
	private List<CommentVo> hotCommentList;		//热门评论
	
	//查询使用
	private String artTypeParam;	//类型
	private int pageNo = 1;
	private int pageSize = 10;
	private Date searchDate;
	private int offset = 0;
	private String searchDateStr;
	
	public String getSearchDateStr() {
		return searchDateStr;
	}
	public void setSearchDateStr(String searchDateStr) {
		this.searchDateStr = searchDateStr;
	}
	public int getOffset() {
		offset = (this.pageNo-1)*pageSize;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Date getSearchDate() {
		if(StringUtils.isNotBlank(searchDateStr)) {
			try {
				searchDate = DateUtils.parseDate(searchDateStr, "yyyy/MM/dd HH:mm:ss");
			} catch (ParseException e) {
				searchDate = new Date();
			}
		}
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public ArtWorksIndexPage() {
		
	}
	public ArtWorksIndexPage(String id,String artTypeParam) {
		this.id = id;
		this.artTypeParam = artTypeParam;
	}
	
	public String getArtTypeParam() {
		return artTypeParam;
	}

	public void setArtTypeParam(String artTypeParam) {
		this.artTypeParam = artTypeParam;
	}

	public ArtWorksIndexPage(String id) {
		this.id = id;
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
	public int getDzNum() {
		return dzNum;
	}
	public void setDzNum(int dzNum) {
		this.dzNum = dzNum;
	}
	public int getPlNum() {
		return plNum;
	}
	public void setPlNum(int plNum) {
		this.plNum = plNum;
	}
	public List<CommentVo> getHotCommentList() {
		return hotCommentList;
	}
	public void setHotCommentList(List<CommentVo> hotCommentList) {
		this.hotCommentList = hotCommentList;
	}
}
