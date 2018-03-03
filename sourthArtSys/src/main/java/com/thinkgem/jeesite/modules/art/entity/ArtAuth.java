package com.thinkgem.jeesite.modules.art.entity;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 艺术认证Entity
 */
public class ArtAuth extends DataEntity<ArtAuth>{

	private static final long serialVersionUID = 1L;

	private String unionId;
	private String name;
	private String idCard;
	private String phone;
	private String artType;
	private String artLevel;
	private String status;
	private String files;
	private String feePayStatus;//认证费用支付状态
	private String feePayNo;	//认证费用支付订单号
	private List<ArtAuthImg> imgList;	//认证图片
	
	
	private Date beginDate;		//开始日期
	private Date endDate;		//截止日期
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getFeePayStatus() {
		return feePayStatus;
	}
	public void setFeePayStatus(String feePayStatus) {
		this.feePayStatus = feePayStatus;
	}
	public String getFeePayNo() {
		return feePayNo;
	}
	public void setFeePayNo(String feePayNo) {
		this.feePayNo = feePayNo;
	}
	public List<ArtAuthImg> getImgList() {
		return imgList;
	}
	public void setImgList(List<ArtAuthImg> imgList) {
		this.imgList = imgList;
	}
}
