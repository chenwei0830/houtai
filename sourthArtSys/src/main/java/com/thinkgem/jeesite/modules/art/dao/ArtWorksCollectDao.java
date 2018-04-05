package com.thinkgem.jeesite.modules.art.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksCollect;

/**
 * 艺术作品收藏DAO接口
 * @author CW
 */
@MyBatisDao
public interface ArtWorksCollectDao extends CrudDao<ArtWorksCollect>{
	void insertCollection(ArtWorksCollect collect);
	void deleteCollection(ArtWorksCollect collect);
}
