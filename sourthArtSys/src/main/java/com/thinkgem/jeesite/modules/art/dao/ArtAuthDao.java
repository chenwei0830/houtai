package com.thinkgem.jeesite.modules.art.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;

/**
 * 认证DAO接口
 * @author LM
 */
@MyBatisDao
public interface ArtAuthDao extends CrudDao<ArtAuth>{

	void insertAuthImg(ArtAuthImg artAuthImg);
	List<ArtAuthImg> getArtAuthImgList(ArtAuth artAuth);
	void deleteAllImgByAuthId(@Param("authId") String authId);
}
