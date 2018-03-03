package com.thinkgem.jeesite.modules.art.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.art.dao.ArtWorksCommentDao;
import com.thinkgem.jeesite.modules.art.entity.ArtWorksComment;

/**
 * 评论Service
 * @author LM
 */
@Service
@Transactional(readOnly = true)
public class ArtWorksCommentService extends CrudService<ArtWorksCommentDao, ArtWorksComment>{

}
