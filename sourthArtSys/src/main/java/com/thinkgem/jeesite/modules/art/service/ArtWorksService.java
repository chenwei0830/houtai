package com.thinkgem.jeesite.modules.art.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorks;

/**
 * 艺术作品Service
 * @author LM
 */
@Service
@Transactional(readOnly = true)
public class ArtWorksService extends CrudService<ArtWorksDao, ArtWorks>{

}
