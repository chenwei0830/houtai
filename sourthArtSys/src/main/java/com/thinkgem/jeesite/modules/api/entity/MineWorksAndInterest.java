package com.thinkgem.jeesite.modules.api.entity;


/**
 * 个人中心艺术作品及收藏信息Entity
 */
public class MineWorksAndInterest {

	private int artWorksCount;		//艺术作品数
	private int collectCount;		//收藏数
	private int interestArtCount;	//关注数
	private int hasNewArtWorks;		//是否有新作品（即待审核）
	
	
	public int getArtWorksCount() {
		return artWorksCount;
	}
	public void setArtWorksCount(int artWorksCount) {
		this.artWorksCount = artWorksCount;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public int getInterestArtCount() {
		return interestArtCount;
	}
	public void setInterestArtCount(int interestArtCount) {
		this.interestArtCount = interestArtCount;
	}
	public int getHasNewArtWorks() {
		return hasNewArtWorks;
	}
	public void setHasNewArtWorks(int hasNewArtWorks) {
		this.hasNewArtWorks = hasNewArtWorks;
	}
	
}
