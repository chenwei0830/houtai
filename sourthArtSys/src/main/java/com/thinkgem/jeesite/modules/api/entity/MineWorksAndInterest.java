package com.thinkgem.jeesite.modules.api.entity;


/**
 * 个人中心艺术作品及收藏信息Entity
 */
public class MineWorksAndInterest {

	private int artWorksCount;		//艺术作品数
	private int newArtWorkCount;	//待审核的作品数
	private int collectCount;		//收藏数
	private int interestArtCount;	//关注数
	private int authCount;			//认证数
	
	public int getAuthCount() {
		return authCount;
	}

	public void setAuthCount(int authCount) {
		this.authCount = authCount;
	}

	public MineWorksAndInterest() {
	}
	
	public MineWorksAndInterest(int artWorksCount,int newArtWorkCount,int collectCount,int interestArtCount,int authCount) {
		this.artWorksCount = artWorksCount;
		this.newArtWorkCount = newArtWorkCount;
		this.collectCount = collectCount;
		this.interestArtCount = interestArtCount;
		this.authCount = authCount;
	}
	public int getNewArtWorkCount() {
		return newArtWorkCount;
	}
	public void setNewArtWorkCount(int newArtWorkCount) {
		this.newArtWorkCount = newArtWorkCount;
	}
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
	
}
