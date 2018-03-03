package com.thinkgem.jeesite.modules.art.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.QiNiuUtils;
import com.thinkgem.jeesite.modules.art.dao.ArtAuthDao;
import com.thinkgem.jeesite.modules.art.entity.ArtAuth;
import com.thinkgem.jeesite.modules.art.entity.ArtAuthImg;

/**
 * 认证Service
 * @author LM
 */
@Service
@Transactional(readOnly = true)
public class ArtAuthService extends CrudService<ArtAuthDao, ArtAuth>{

	/**
	 * 保存认证图片
	 * @param fileIds 图片数组ID
	 * @param authId 认证ID
	 */
	@Transactional(readOnly = false)
	public void saveAuthImgs(String[] fileIds,String authId) {
		for(String fileId : fileIds) {
			String imgUrl = QiNiuUtils.QN_URL+"/"+fileId;
			ArtAuthImg authImg = new ArtAuthImg();
			authImg.preInsert();
			authImg.setUserAuthId(authId);
			authImg.setImgUrl(imgUrl);
			dao.insertAuthImg(authImg);
		}
	}
	
	public List<ArtAuthImg> getArtAuthImgList(ArtAuth artAuth){
		return dao.getArtAuthImgList(artAuth);
	}
}
