package com.thinkgem.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.OrgDao;
import com.thinkgem.jeesite.modules.sys.entity.Org;

/**
 * 组织机构Service
 * @author LM
 */
@Service
@Transactional(readOnly = true)
public class OrgService extends CrudService<OrgDao, Org>{

}
