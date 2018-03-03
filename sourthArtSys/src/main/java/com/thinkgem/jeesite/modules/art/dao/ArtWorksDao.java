package com.thinkgem.jeesite.modules.art.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;

/**
 * 艺术作品DAO接口
 * @author LM
 */
@MyBatisDao
public interface ArtWorksDao extends CrudDao<ArtWorks>{

}
