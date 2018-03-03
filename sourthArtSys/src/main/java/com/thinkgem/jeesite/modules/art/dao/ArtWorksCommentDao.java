package com.thinkgem.jeesite.modules.art.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksComment;

/**
 * 评论DAO接口
 * @author LM
 */
@MyBatisDao
public interface ArtWorksCommentDao extends CrudDao<ArtWorksComment>{

}
