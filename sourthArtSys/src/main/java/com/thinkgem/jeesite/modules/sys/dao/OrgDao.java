package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;

/**
 * 组织机构DAO接口
 * @author LM
 */
@MyBatisDao
public interface OrgDao extends CrudDao<Org>{

}
