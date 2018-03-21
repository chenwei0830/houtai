package com.thinkgem.jeesite.modules.art.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.entity.MineArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksContent;

/**
 * 艺术作品DAO接口
 * @author LM
 */
@MyBatisDao
public interface ArtWorksDao extends CrudDao<ArtWorks>{

	int insertArtWorkContent(ArtWorksContent art);
	
	List<Integer> getMineCountInfo(@Param("openId")String openId);
	
	List<MineArtWorks> getMineArtWorksList(MineArtWorks mineArtWorks);
}
